<template>
  <v-dialog v-model="reviewDialog" max-width="750">
    <template
      v-slot:activator="{ on, attrs }"
      v-if="this.$route.name != 'CafeDetailPage'"
    >
      <button
        class="modify-button"
        type="button"
        aria-label="수정하기"
        v-on="on"
        v-bind="attrs"
      >
        <v-icon aria-hidden="true">mdi-pencil</v-icon>
      </button>
    </template>

    <v-card>
      <div class="review-card">
        <div class="review-card-title">
          리뷰 쓰기
          <button @click="close()" type="button" aria-label="리뷰닫기">
            <i class="icClose"></i>
          </button>
        </div>

        <div class="review-card-point">
          포토리뷰&nbsp;
          <span> 150P </span>,&nbsp;일반리뷰&nbsp;
          <span>0P</span>
        </div>

        <div class="review-card-star">
          <div class="review-card-content header">별점 평가</div>
          <div class="review-card-star wrap">
            <div class="review-card-star-label">만족도</div>
            <star-rating
              v-model="star_score"
              :show-rating="false"
              :rounded-corners="true"
              inactive-color="#e0e2e7"
              active-color="#fee500"
              :star-points="[
                23, 2, 14, 17, 0, 19, 10, 34, 7, 50, 23, 43, 38, 50, 36, 34, 46,
                19, 31, 17,
              ]"
            ></star-rating>
          </div>
        </div>
      </div>

      <div class="review-file">
        <div class="review-card-content header">사진 첨부(선택)</div>
        <div class="review-card-explain">사진을 첨부해주세요(최대 1장)</div>
        <div class="review-card-btn">
          <div class="select-picture" v-if="image">
            <img
              class="select-picture-contents"
              :src="image"
              alt="첨부한 사진"
            />

            <button
              @click="imageDelete()"
              class="delete-button"
              type="button"
              aria-label="첨부 파일 삭제하기"
            >
              <i class="icClose" aria-hidden="true"></i>
            </button>
          </div>

          <input
            class="visually-hidden"
            id="files"
            type="file"
            ref="files"
            multiple
            v-if="uploadReady"
            v-on:change="reviewFileUpload()"
          />
          <v-btn
            class="btn-outlined btn-40"
            type="button"
            @click="onUpload()"
            aria-label="사진 첨부하기"
          >
            사진 첨부하기
          </v-btn>
        </div>
      </div>

      <div class="review-form">
        <div class="review-card-content">
          <div class="review-card-content header">리뷰 작성</div>
          <textarea
            v-model="review_content"
            style="border-radius: 4px"
            class="form-control"
            minlength="20"
            maxlength="1000"
            cols="120"
            rows="32"
            placeholder="자세하고 솔직한 리뷰는 다른 고객에게 큰 도움이 됩니다.(최소 20자 이상)"
          ></textarea>
        </div>

        <div class="review-card-btn">
          <v-btn class="btn-indigo btn-40" @click="onModify" type="submit"
            >수정</v-btn
          >
        </div>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
import StarRating from "vue-star-rating"
import { mapActions } from "vuex" //

export default {
  name: "CafeReviewModify",
  components: {
    StarRating,
  },
  props: {
    review: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      image: "",
      files: "",
      review_content: "",
      star_score: "",
      uploadReady: true,
      reviewDialog: false,
      cafeNum: "",
      fileName: this.review.fileName,
      reviewNo: this.review.reviewNo,
    }
  },
  created() {
    this.reviewNo = this.review.reviewNo
    this.cafeNum = this.review.cafeNo
    this.star_score = this.review.star_score
    this.review_content = this.review.review_content
    this.fileName = this.review.fileName
  },
  methods: {
    ...mapActions(["fetchReviewList", "fetchReview"]),

    imageDelete() {
      this.image = null
      this.clearImage()
    },

    clearImage() {
      this.uploadReady = false
      this.$nextTick(() => {
        this.uploadReady = true
      })
    },
    onUpload() {
      this.$refs.files.click()
    },
    reviewFileUpload() {
      var image = this.$refs["files"].files[0]
      this.image = URL.createObjectURL(image)
      this.files = this.$refs.files.files[0]
    },
    onModify() {
      const { reviewNo, star_score, review_content, cafeNum } = this
      const file = this.$refs.files.files[0]
      this.$emit("submit", {
        reviewNo,
        star_score,
        review_content,
        cafeNum,
        file,
      })
      console.log(reviewNo, star_score, review_content, cafeNum, file)
    },
    close() {
      this.reviewDialog = false
      this.$emit("input", this.reviewDialog)
    },
  },
}
</script>

<style lang="scss" scoped>
@import "~@/assets/scss/components/cafe/cafe-review-dialog";
.modify-button {
  @include inline-flexbox;
  position: absolute;
  top: 4px;
  right: 18px;
  width: 44px;
  height: 44px;
  color: $secondary;
  &:hover,
  &:active {
    opacity: 0.4;
  }
}
</style>
