<template>
  <div class="mysidebar">
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoOne }}</h1>
      </header>
      <ul>
        <li class="mysidebar-list" v-for="item in sidebarOne" :key="item.id">
          <router-link class="mysidebar-link" :to="item.link">
            <div class="mysidebar-test">
              <v-icon>{{ item.icon }}</v-icon>
              <p>{{ item.title }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </div>
    <div class="mysidebar-content">
      <header>
        <h1>{{ infoTwo }}</h1>
      </header>
      <ul>
        <template v-if="social == 'LOCAL'">
          <li class="mysidebar-list" v-for="item in sidebarTwo" :key="item.id">
            <template v-if="item.id == 3">
              <router-link
                class="mysidebar-link"
                :to="item.link"
                target="_blank"
              >
                <div class="mysidebar-test">
                  <v-icon>{{ item.icon }}</v-icon>
                  <p>{{ item.title }}</p>
                </div>
              </router-link>
            </template>
            <template v-else>
              <router-link class="mysidebar-link" :to="item.link">
                <div class="mysidebar-test">
                  <v-icon>{{ item.icon }}</v-icon>
                  <p>{{ item.title }}</p>
                </div>
              </router-link>
            </template>
          </li>
        </template>
        <template v-else>
          <li
            class="mysidebar-list"
            v-for="item in sidebarThree"
            :key="item.id"
          >
            <template v-if="item.id == 2">
              <router-link
                class="mysidebar-link"
                :to="item.link"
                target="_blank"
              >
                <div class="mysidebar-test">
                  <v-icon>{{ item.icon }}</v-icon>
                  <p>{{ item.title }}</p>
                </div>
              </router-link>
            </template>
            <template v-else>
              <router-link class="mysidebar-link" :to="item.link">
                <div class="mysidebar-test">
                  <v-icon>{{ item.icon }}</v-icon>
                  <p>{{ item.title }}</p>
                </div>
              </router-link>
            </template>
          </li>
        </template>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: "UserSidebar",
  data() {
    return {
      social: this.$store.state.user.socialType,
      sidebarOne: [
        {
          id: 2,
          icon: "mdi-format-list-bulleted",
          title: "주문내역",
          link: "/user/history",
        },
        {
          id: 3,
          icon: "mdi-message-text-outline",
          title: "내리뷰",
          link: "/user/review",
        },
        {
          id: 4,
          icon: "mdi-heart-outline",
          title: "좋아요",
          link: "/user/like",
        },
      ],
      sidebarTwo: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "내 정보수정",
          link: "/user/modify",
        },
        {
          id: 2,
          icon: "mdi-lock",
          title: "비밀번호 변경",
          link: "/user/pw",
        },
        {
          id: 3,
          icon: "mdi-email-outline",
          title: "문의사항",
          link: "/chat",
        },
      ],
      sidebarThree: [
        {
          id: 1,
          icon: "mdi-account-outline",
          title: "내 정보수정",
          link: "/user/modify",
        },
        {
          id: 2,
          icon: "mdi-email-outline",
          title: "문의사항",
          link: "/chat",
        },
      ],
      infoOne: "나의 활동",
      infoTwo: "내 정보",
    }
  },
}
</script>
<style lang="scss" scoped>
@import "~@/assets/scss/components/my-sidebar";
</style>
