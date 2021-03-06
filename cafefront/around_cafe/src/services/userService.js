//import Vue from 'vue'
//import VueCookies from "vue-cookies";

//Vue.use(VueCookies)
//Vue.$cookies.config("14d");

class UserService {
  setUserInfo(userInfo) {
    this.deleteUserInfo()
    const user = {
      memNo: userInfo["memNo"],
      nickname: userInfo["nickname"],
      email: userInfo["email"],
      role: userInfo["role"],
      cafeNo: userInfo["cafeNo"],
    }
    localStorage.setItem("user", JSON.stringify(user))
  }

  getUserInfo() {
    const userInfo = JSON.parse(localStorage.getItem("user"))
    return {
      memNo: userInfo["memNo"],
      nickname: userInfo["nickname"],
      email: userInfo["email"],
      role: userInfo["role"],
      cafeNo: userInfo["cafeNo"],
    }
  }

  deleteUserInfo() {
    localStorage.removeItem("user")
  }
}

export default new UserService()
