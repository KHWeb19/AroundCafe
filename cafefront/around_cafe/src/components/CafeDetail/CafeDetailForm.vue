<template>
  <div class="detail">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <!-- 카페이름  -->
          <div class="detail-container">
            <header>
              <h1>{{ cafeBoard.cafe_name }}</h1>
            </header>
            <div class="image-grid">
              <picture
                v-for="(img, index) in cafeBoard.cafeImgs.slice(0, 5)"
                :key="index"
                :class="{ row: index === 0 }"
              >
                <source />
                <template v-if="index < 5">
                  <img
                    :src="require(`@/assets/cafe/cafeMypage/${img.cafe_img}`)"
                    :alt="`이미지${img.cafeImgNo}`"
                  />
                </template>
              </picture>
            </div>

            <div class="contaianer">
              <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-8">
                  <div>
                    <div class="detail-content">
                      <div class="detail-info">
                        <div>
                          <v-icon aria-hidden="true">mdi-phone</v-icon>
                        </div>
                        <div>{{ cafeBoard.cafe_call }}</div>
                      </div>
                      <div class="detail-info">
                        <div>
                          <v-icon aria-hidden="true"
                            >mdi-map-marker-outline</v-icon
                          >
                        </div>
                        <div>
                          <div>
                            <span>{{ cafeBoard.address }}</span>
                          </div>
                        </div>
                      </div>
                      <div class="detail-info">
                        <div>
                          <span>
                            <v-icon aria-hidden="true">mdi-av-timer</v-icon>
                          </span>
                        </div>
                        <div>{{ cafeBoard.cafe_time }}</div>
                      </div>
                    </div>
                    <div class="detail-content">
                      <h3 class="detail-int-header">카페소개</h3>
                      <div class="detail-int-text">
                        <span>{{ cafeBoard.cafe_content }} </span>
                      </div>
                      <div class="detail-int-btn">
                        <button>
                          <span>
                            <span class="view">더 보기</span>
                            <i class="icChevron"></i>
                          </span>
                        </button>
                      </div>
                    </div>
                    <div class="detail-content">
                      <h3 class="detail-int-header">메뉴</h3>
                      <CafeSiteMenuList
                        @submitCheckBasketList="submitCheckBasketList"
                        :cafeNo="cafeNo"
                        :menuLists="menuLists"
                      />
                    </div>
                    <div class="detail-content">
                      <section class="detail-section">
                        <header class="detail-section-header">
                          <h1 class="review-title">리뷰</h1>
                          <strong
                            class="badge"
                            :aria-label="reviewList.length"
                            >{{ reviewList.length }}</strong
                          >
                          <CafeReviewRegisterPage
                            class="text-btn"
                            :cafeNo="cafeNo"
                          />
                        </header>

                        <div class="review-scoreboard">
                          <div class="score-summary">
                            <strong class="average-scroe">
                              <StarRating
                                v-model="this.starAver"
                                :inline="true"
                                :increment="0.1"
                                :read-only="true"
                                :star-size="32"
                                :incremane="0.1"
                                active-color="#fee500"
                                :star-points="[
                                  23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43,
                                  38, 50, 36, 34, 46, 19, 31, 17,
                                ]"
                              ></StarRating>
                            </strong>
                          </div>
                          <div class="score-detail">
                            <dl class="score-stats-list">
                              <div class="score-stats-item">
                                <dt>5점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden="true">
                                    <div class="active-bar" :style="bar5"></div>
                                  </div>
                                  <strong
                                    class="count"
                                    :aria-label="this.StarPoint5"
                                    >{{ this.StarPoint5 }}</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>4점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden="true">
                                    <div class="active-bar" :style="bar4"></div>
                                  </div>
                                  <strong
                                    class="count"
                                    :aria-label="this.StarPoint4"
                                    >{{ this.StarPoint4 }}</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>3점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden="true">
                                    <div class="active-bar" :style="bar3"></div>
                                  </div>
                                  <strong
                                    class="count"
                                    :aria-label="this.StarPoint3"
                                    >{{ this.StarPoint3 }}</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>2점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden="true">
                                    <div class="active-bar" :style="bar2"></div>
                                  </div>
                                  <strong
                                    class="count"
                                    :aria-label="this.StarPoint2"
                                    >{{ this.StarPoint2 }}</strong
                                  >
                                </dd>
                              </div>

                              <div class="score-stats-item">
                                <dt>1점</dt>
                                <dd>
                                  <div class="bar-graph" aria-hidden="true">
                                    <div class="active-bar" :style="bar1"></div>
                                  </div>
                                  <strong
                                    class="count"
                                    :aria-label="this.StarPoint1"
                                    >{{ this.StarPoint1 }}</strong
                                  >
                                </dd>
                              </div>
                            </dl>
                          </div>
                        </div>
                        <CafeReviewForm :cafeNo="cafeNo" />
                      </section>
                    </div>
                    <div class="detail-content">
                      <h3 class="detail-int-header">문의하기</h3>
                      <CafeDetailQnaForm
                        :cafeNo="cafeNo"
                        :cafeBoard="cafeBoard"
                      />
                    </div>
                  </div>
                </div>
                <div class="col-lg-4 lg-only">
                  <CafeDetailSidebar
                    :cafeBoard="cafeBoard"
                    :basketList="basketList"
                    :cafeNo="cafeNo"
                    @input="deleteMenu"
                  />
                </div>
              </div>
              <div class="detail-content">
                <h3 class="detail-int-header">위치보기</h3>
                <MapKakaoFind :cafeBoard="cafeBoard" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CafeDetailQnaForm from "@/components/CafeDetail/CafeDetailQnaForm.vue"
import CafeDetailSidebar from "@/components/CafeDetail/CafeDetailSidebar.vue"
import CafeReviewRegisterPage from "@/views/Cafe/CafeReviewRegisterPage.vue"
import CafeReviewForm from "@/components/CafeReview/CafeReviewForm.vue"
import CafeSiteMenuList from "../CafeSite/CafeSiteMenuList.vue"
import MapKakaoFind from "@/components/Map/MapKakaoFind.vue"
import StarRating from "vue-star-rating"
import { mapState, mapActions } from "vuex"
import axios from "axios"

export default {
  name: "CafeDetailForm",
  components: {
    StarRating,
    CafeReviewForm,
    CafeDetailSidebar,
    CafeSiteMenuList,
    MapKakaoFind,
    CafeReviewRegisterPage,
    CafeDetailQnaForm,
  },
  props: {
    cafeBoard: {
      type: Object,
      required: true,
    },
    cafeNo: {
      type: String,
      required: true,
    },
  },

  data() {
    return {
      reviewNo: "",
      basketList: [],
      page: "",
      starSum: 0,
      starAver: 0,
      ReviewsQuantity: 0,
      StarPoint1: 0,
      StarPoint2: 0,
      StarPoint3: 0,
      StarPoint4: 0,
      StarPoint5: 0,
      StarPoint1Persent: 0,
      StarPoint2Persent: 0,
      StarPoint3Persent: 0,
      StarPoint4Persent: 0,
      StarPoint5Persent: 0,
      reviewList: [],
      memNo: this.$store.state.user.memNo,
    }
  },

  computed: {
    ...mapState(["menuLists"]),
    bar1() {
      return `width:${this.StarPoint1Persent}%`
    },
    bar2() {
      return `width:${this.StarPoint2Persent}%`
    },
    bar3() {
      return `width:${this.StarPoint3Persent}%`
    },
    bar4() {
      return `width:${this.StarPoint4Persent}%`
    },
    bar5() {
      return `width:${this.StarPoint5Persent}%`
    },
  },
  created() {
    this.fetchMenuLists(this.cafeNo)
    if (this.memNo == null) this.memNo = 0
    axios
      .get(
        `http://localhost:7777/cafe/review/list/${this.cafeNo}/${this.memNo}`
      )
      .then((res) => {
        this.reviewList = res.data
        this.calculateStarAver()
        this.calculatePersentage()
      })
  },

  methods: {
    ...mapActions(["fetchMenuLists"]),
    submitCheckBasketList(CheckBasketList) {
      this.basketList = CheckBasketList
    },
    calculateStarAver() {
      console.log(this.reviewList.length)
      this.ReviewsQuantity = this.reviewList.length
      for (let i = 0; i < this.ReviewsQuantity; i++) {
        this.starSum += parseInt(this.reviewList[i].star_score)
      }
      if (this.starSum == 0) {
        this.starAver = 0
      } else {
        this.starAver = this.starSum / this.ReviewsQuantity
      }
      console.log("평균별점")
      console.log(this.starAver)
    },
    calculatePersentage() {
      this.ReviewsQuantity = this.reviewList.length
      for (let i = 0; i < this.ReviewsQuantity; i++) {
        if (this.reviewList[i].star_score == "1") {
          this.StarPoint1 += 1
        } else if (this.reviewList[i].star_score == "2") {
          this.StarPoint2 += 1
        } else if (this.reviewList[i].star_score == "3") {
          this.StarPoint3 += 1
        } else if (this.reviewList[i].star_score == "4") {
          this.StarPoint4 += 1
        } else if (this.reviewList[i].star_score == "5") {
          this.StarPoint5 += 1
        }
      }
      this.StarPoint1Persent = (this.StarPoint1 / this.ReviewsQuantity) * 100
      this.StarPoint2Persent = (this.StarPoint2 / this.ReviewsQuantity) * 100
      this.StarPoint3Persent = (this.StarPoint3 / this.ReviewsQuantity) * 100
      this.StarPoint4Persent = (this.StarPoint4 / this.ReviewsQuantity) * 100
      this.StarPoint5Persent = (this.StarPoint5 / this.ReviewsQuantity) * 100
    },
    deleteMenu(payload) {
      this.basketList.splice(payload.index, 1)
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/detail/detail";
@import "~@/assets/scss/components/detail/detail-content";
@import "~@/assets/scss/components/detail/detail-grid";
@import "~@/assets/scss/components/review/review-scoreboard";
@import "~@/assets/scss/components/review/score-stats";
</style>
