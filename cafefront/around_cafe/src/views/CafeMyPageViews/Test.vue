<template>
  <div>
      <div v-if="!files.length" class="room-file-upload-example-container">
          <div class="room-file-upload-example">
              <div class="room-file-notice-item room-file-upload-button">
                  <div class="image-box">
                      <!-- <div class="image-profile">
                              <img :src="profileImage" />
                              </div>-->
                      <label for="file">일반 사진 등록</label>
                      <input type="file" id="file" ref="files" @change="imageUpload" multiple />
                  </div>
              </div>
          </div>
      </div>
      <div v-else class="file-preview-content-container">
          <div class="file-preview-container">
              <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                  <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                      x
                  </div>
                  <img :src="file.preview" />
              </div>
              <div class="file-preview-wrapper-upload">
                  <div class="image-box">
                      <label for="file">추가 사진 등록</label>
                      <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                  </div>
                  <!-- <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div> -->
              </div>
          </div>
      </div>
      <div>
        <v-btn @click="submitImg">등록하기</v-btn>
      </div>
  </div>
</template>
<script>
import axios from 'axios'
export default {
   data() {
      return {
          files: [], //업로드용 파일
          filesPreview: [],
          uploadImageIndex: 0 // 이미지 업로드를 위한 변수
      }
    },
    methods: {
        imageUpload() {
            console.log(this.$refs.files.files);

            // this.files = [...this.files, this.$refs.files.files];
            //하나의 배열로 넣기
            let num = -1;
            for (let i = 0; i < this.$refs.files.files.length; i++) {
                this.files = [
                    ...this.files,
                    //이미지 업로드
                    {
                        //실제 파일
                        file: this.$refs.files.files[i],
                        //이미지 프리뷰
                        preview: URL.createObjectURL(this.$refs.files.files[i]),
                        //삭제및 관리를 위한 number
                        number: i
                    }
                ];
                num = i;
                //이미지 업로드용 프리뷰
                // this.filesPreview = [
                //   ...this.filesPreview,
                //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
                // ];
            }
            this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
            console.log(this.files);
            // console.log(this.filesPreview);
        },

        imageAddUpload() {
            console.log(this.$refs.files.files);

            // this.files = [...this.files, this.$refs.files.files];
            //하나의 배열로 넣기c
            let num = -1;
            for (let i = 0; i < this.$refs.files.files.length; i++) {
                console.log(this.uploadImageIndex);
                this.files = [
                    ...this.files,
                    //이미지 업로드
                    {
                        //실제 파일
                        file: this.$refs.files.files[i],
                        //이미지 프리뷰
                        preview: URL.createObjectURL(this.$refs.files.files[i]),
                        //삭제및 관리를 위한 number
                        number: i + this.uploadImageIndex
                    }
                ];
                num = i;
            }
            this.uploadImageIndex = this.uploadImageIndex + num + 1;

            console.log(this.files);
            // console.log(this.filesPreview);
        },
        fileDeleteButton(e) {
            const name = e.target.getAttribute('name');
            this.files = this.files.filter(data => data.number !== Number(name));
            // console.log(this.files);
        },
        submitImg(){
          let formData = new FormData()

          if(this.files.length > 0) {
                for(let idx = 0; idx <this.files.length; idx++) {
                    formData.append('fileList', this.files[idx])
                }
          }
          let membNo = 1;
            axios.put(`http://localhost:7777/cafe/modify/${membNo}`, formData)
                    .then(() => {
                        alert('수정되었습니다!')
                        this.$router.go()
                    })
                    .catch(() => {
                        alert('수정 실패!')
                    })

        }
    }
}
</script>
<style scoped>
.main-container {
            width: 1200px;
            height: 400px;
            margin: 0 auto;
        }
        
        .room-deal-information-container {
            margin-top: 50px;
            color: #222222;
            border: 1px solid #dddddd;
        }
        
        .room-deal-information-title {
            text-align: center;
            font-size: 18px;
            line-height: 60px;
            border-bottom: 1px solid #dddddd;
        }
        
        .room-deal-information-content-wrapper {
            min-height: 50px;
            display: flex;
        }
        
        .room-deal-informtaion-content-title {
            font-size: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            width: 150px;
            background-color: #f9f9f9;
        }
        
        .room-deal-information-content {
            width: 100%;
            font-size: 14px;
        }
        
        .room-deal-option-selector {
            display: flex;
            align-items: center;
            padding: 15px;
        }
        
        .room-deal-option-item {
            width: 100px;
            height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 1px solid #cccccc;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .room-deal-option-item:last-child {
            margin-left: 10px;
        }
        
        .room-deal-option-notice {
            margin-left: auto;
            font-size: 14px;
            color: #888888;
        }
        
        .room-deal-option-item-deposit {
            margin-left: 10px;
        }
        
        .room-deal-information-wrapper {
            display: flex;
            flex-direction: column;
        }
        
        .room-deal-information-option {
            padding: 10px;
            display: flex;
            align-items: center;
        }
        
        .room-deal-information-option:last-child {
            border-bottom: 1px solid #dddddd;
        }
        
        .room-deal-information-item-type {
            font-size: 13px;
            color: #fff;
            background-color: #61b6e5;
            min-width: 50px;
            height: 26px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 3px;
        }
        
        .room-deal-information-item-wrapper {
            display: flex;
            align-items: center;
            margin-left: 10px;
            height: 46px;
            width: 100%;
        }
        
        .room-deal-information-item-wrapper>input {
            border: 1px solid #dddddd;
            width: 140px;
            height: 100%;
            padding: 0 15px;
            font-size: 15px;
        }
        
        .room-deal-inforamtion-won {
            margin: 0 10px;
        }
        
        .room-deal-information-example {
            color: #888888;
        }
        
        .room-deal-information-option:not(:first-child) {
            margin-top: 10px;
        }
        
        .room-deal-inforamtion-divide {
            font-size: 22px;
            margin: 0 8px;
            color: #222222;
            font-weight: 100;
        }
        
        .room-deal-close-button-wrapper {
            margin-left: auto;
            cursor: pointer;
        }
        
        .room-deal-close-button {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 22px;
            height: 22px;
            background-color: #666666;
            color: rgb(220, 220, 220);
        }
        
        .room-deal-cliked {
            background-color: rgb(235, 235, 235);
            color: rgb(170, 170, 170);
        }
        
        .room-file-upload-example {
            height: 100%;
        }
        
        .room-write-content-container {
            border-top: 1px solid #dddddd;
            min-height: 260px;
        }
        
        .room-picture-notice {
            margin: 20px;
            padding: 20px 40px;
            border: 1px solid #dddddd;
        }
        
        .file-preview-content-container {
            height: 100%;
        }
        
        .room-file-upload-wrapper {
            margin: 20px;
            border: 1px solid #dddddd;
            background-color: #f4f4f4;
            min-height: 350px;
            font-size: 15px;
            color: #888888;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
        }
        
        .room-file-upload-example-container {
            display: flex;
            align-items: center;
            justify-content: center;
            /* height: 100%;
  width: 100%; */
        }
        
        .room-file-image-example-wrapper {
            text-align: center;
        }
        
        .room-file-notice-item {
            margin-top: 5px;
            text-align: center;
        }
        
        .room-file-notice-item-red {
            color: #ef4351;
        }
        
        .image-box {
            margin-top: 30px;
            padding-bottom: 20px;
            text-align: center;
        }
        
        .image-box input[type='file'] {
            position: absolute;
            width: 0;
            height: 0;
            padding: 0;
            overflow: hidden;
            border: 0;
        }
        
        .image-box label {
            display: inline-block;
            padding: 10px 20px;
            background-color: #232d4a;
            color: #fff;
            vertical-align: middle;
            font-size: 15px;
            cursor: pointer;
            border-radius: 5px;
        }
        
        .file-preview-wrapper {
            padding: 10px;
            position: relative;
        }
        
        .file-preview-wrapper>img {
            position: relative;
            width: 190px;
            height: 130px;
            z-index: 10;
        }
        
        .file-close-button {
            position: absolute;
            /* align-items: center; */
            line-height: 18px;
            z-index: 99;
            font-size: 18px;
            right: 5px;
            top: 10px;
            color: #fff;
            font-weight: bold;
            background-color: #666666;
            width: 20px;
            height: 20px;
            text-align: center;
            cursor: pointer;
        }
        
        .file-preview-container {
            height: 100%;
            display: flex;
            flex-wrap: wrap;
        }
        
        .file-preview-wrapper-upload {
            margin: 10px;
            padding-top: 20px;
            background-color: #888888;
            width: 190px;
            height: 130px;
        }
        
        .room-write-button-wrapper {
            margin-top: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #222222;
        }
        
        .room-write-button-wrapper>div {
            width: 160px;
            height: 50px;
            border: 1px solid #dddddd;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 15px;
            cursor: pointer;
        }
        
        .room-write-button {
            margin-left: 15px;
            color: #fff;
            background-color: #1564f9;
        }
        
        .room-write-button:hover {
            opacity: 0.8;
        }
</style>