<template>
  <div class="menu-list-content">
    <ul class="menu-list">
      <li v-for="item in listData" :key="item.menu_no" class="menu-item">
        <a href="">
          <div class="menu-card">
            <div class="menu-card-image">
              <template>
                <img
                  position="relative"
                  v-if="item.menu_img != null"
                  v-bind:src="
                    require(`@/assets/cafe/cafeMenu/${item.menu_img}`)
                  "
                    :alt="`메뉴 이미지${item.menu_no}`"
                />
              </template>
              <template v-if="item.sold_out == true">
                <img
                  position="absolute"
                  v-bind:src="require(`@/assets/images/sold_out2.webp`)"
                  alt="솔드아웃 이미지"
                />
              </template>
              <template>
                <img
                  v-if="item.menu_img == null"
                  v-bind:src="require(`@/assets/cafe/cafeMenu/imgNull.png`)"
                  alt="이미지 없음"
                />
              </template>

              <div v-if="item.signature == true" class="menu-mark">
                <span>Signature</span>
              </div>
            </div>

            <div class="menu-card-info">
              <div class="menu-card-title">{{ item.menu_name }}</div>
              <div class="menu-card-detail">
                <p>
                  {{ item.menu_content }}
                </p>
              </div>
              <div class="menu-card-price">
                {{ item.menu_price | pricePoint }}원
              </div>
            </div>
          </div>
        </a>

        <div class="menu-item-btn">
          <button
            type="button"
            aria-label="장바구니 담기"
            @click="addToBasket(item)"
          >
            <i class="icCart" aria-hidden=true></i>
          </button>
        </div>
      </li>
    </ul>
    <PaginationForm
      :pageSetting="pageDataSetting(total, limit, block, this.page)"
      @paging="pagingMethod"
    />
  </div>
</template>
<script>
import PaginationForm from "@/components/PaginationForm.vue"
//import { mapState, mapActions } from "vuex"

export default {
  name: "CafeMenuList",
  components: { PaginationForm },
  props: {
    cafeNo: {
      type: String,
      required: true,
    },
    menuLists: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      signatureNo: "",
      CheckbasketList: [],
      listData: [],
      page: 1,
      limit: 3,
      block: 5,
      pageNo: "",
      total: "",
      membNo: this.$store.state.user.memNo,
    }
  },
  mounted() {
    setTimeout(() => {
      this.pagingMethod(this.page)
    }, 70)
  },
  methods: {
    addToBasket(item) {
      if (this.membNo == null) {
        alert("로그인을 해주세요")
      } else {
        if (item.sold_out == true) {
          alert("매진된 상품입니다.")
        } else {
          console.log(item)
          if (this.CheckbasketList.length == 0) {
            this.CheckbasketList.push(item)
            console.log("값을 정상적으로 저장하였습니다.")
            this.$emit("submitCheckBasketList", this.CheckbasketList)
          } else {
            for (let i = 0; i <= this.CheckbasketList.length; i++) {
              // console.log(this.CheckbasketList.length)
              if (this.CheckbasketList.includes(item)) {
                alert("해당제품은 이미 장바구니에 담으셨습니다!")
                break
              } else {
                this.CheckbasketList.push(item)
                console.log("값을 정상적으로 저장하였습니다.")
                this.$emit("submitCheckBasketList", this.CheckbasketList)
                break
              }
            }
          }
        }
      }
    },
    pagingMethod(page) {
      this.listData = this.menuLists.slice(
        (page - 1) * this.limit,
        page * this.limit
      )
      this.page = page
      let total = this.menuLists.length
      this.pageDataSetting(total, this.limit, this.block, page)
    },
    pageDataSetting(total, limit, block, page) {
      total = this.menuLists.length
      const totalPage = Math.ceil(total / limit)
      let currentPage = page
      const first =
        currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1, 10) : null
      const end =
        totalPage !== currentPage
          ? parseInt(currentPage, 10) + parseInt(1, 10)
          : null

      let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1
      let endIndex =
        startIndex + block > totalPage ? totalPage : startIndex + block - 1
      let list = []
      for (let index = startIndex; index <= endIndex; index++) {
        list.push(index)
      }
      return { first, end, list, currentPage }
    },
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-menu-list";
</style>
