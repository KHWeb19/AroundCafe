import json
from flask import Blueprint, jsonify, redirect, request
from flask_cors import cross_origin
from flask_restful import abort

from .kakao.KakaoPaymentsService import KakaoPaymentService

paymentBp = Blueprint('paymentBp', __name__)

# 결제 준비


@paymentBp.route('/ready/<string:socialType>/<string:paymentNo>', methods=['GET'])
def readyToPayment(socialType, paymentNo):
    if socialType == "KAKAO":
        paymentInfo = KakaoPaymentService().readyToPayment(paymentNo)
        if(paymentInfo == "zero_amount"):
            KakaoPaymentService().saveConfirmation(paymentNo)
            return redirect("http://localhost:8080/cafe/purchase/popup?paymentNo=%s&paymentResult=success" % paymentNo)
        else:
            KakaoPaymentService().saveTID(paymentNo, paymentInfo['tid'])
            return redirect(paymentInfo["next_redirect_pc_url"])

# 결제 승인(성공시)


@paymentBp.route('/<string:socialType>/success', methods=['GET', 'POST'])
def paymentSuccess(socialType):
    if socialType == "KAKAO":
        paymentNo = request.args.get("paymentNo")
        pg_token = request.args.get("pg_token")
        paymentInfo = KakaoPaymentService().approveToPayment(paymentNo, pg_token)

        KakaoPaymentService().saveConfirmation(paymentNo)

        # 성공페이지로 리다이렉트
        return redirect("http://localhost:8080/cafe/purchase/popup?paymentNo=%s&paymentResult=success" % paymentNo)
        # return paymentInfo

# 결제 거부(실패시)


@paymentBp.route('/<string:socialType>/fail', methods=['GET', 'POST'])
def paymentFail(socialType):
    if socialType == "KAKAO":
        paymentNo = request.args.get("paymentNo")
        paymentInfo = KakaoPaymentService().checkPayment(paymentNo)
        if(paymentInfo['status'] == 'QUIT_PAYMENT'):
            # 실패페이지로 리다이렉트
            return redirect("http://localhost:8080/cafe/purchase/popup?paymentNo=%s&paymentResult=fail" % paymentNo)
        else:
            return redirect("https://www.daum.net")  # 오류페이지로 리다이렉트


# 결제 거부(취소시)
@paymentBp.route('/<string:socialType>/cancel', methods=['GET', 'POST'])
def paymentCancel(socialType):
    if socialType == "KAKAO":
        paymentNo = request.args.get("paymentNo")
        paymentInfo = KakaoPaymentService().checkPayment(paymentNo)
        if(paymentInfo['status'] == 'QUIT_PAYMENT'):
            # 실패페이지로 리다이렉트
            return redirect("http://localhost:8080/cafe/purchase/popup?paymentNo=%s&paymentResult=cancel" % paymentNo)
        else:
            return redirect("https://www.daum.net")  # 오류페이지로 리다이렉트


# 결제 취소
@paymentBp.route('/cancel', methods=['POST'])
def cancelPayment():
    data = request.json
    paymentNo = data.get('paymentNo')
    cancelAmount = data.get('cancelAmount')

    response = KakaoPaymentService().calcelPayment(paymentNo, cancelAmount)

    return response
