import React, { useEffect, useState } from "react";
import {
  CreditCardOutlined,
  ReconciliationOutlined,
  CaretRightOutlined,
} from "@ant-design/icons";
import { useNavigate } from "react-router";
import { useParams } from "react-router-dom";
import axios from "axios";

const Checkout = () => {
  const [cardNum1, setCardNum1] = useState("");
  const [cardNum2, setCardNum2] = useState("");
  const [cardNum3, setCardNum3] = useState("");
  const [cardNum4, setCardNum4] = useState("");
  const [cardPw, setCardPw] = useState("");
  const [cardEndDate1, setCardEndDate1] = useState("");
  const [cardEndDate2, setCardEndDate2] = useState("");
  const [period, setPeriod] = useState("");
  const [birthday, setBirthday] = useState("");
  const [address, setAddress] = useState("");
  const [addressDesc, setAddressDesc] = useState("");
  const [paymentamount, setPaymentAmount] = useState(0);

  let navigate = useNavigate();
  let { rewardId, rewardTypeId } = useParams();
  const [rewardType, setRewardType] = useState(null);
  const [totInfo, setTotInfo] = useState();

  useEffect(() => {
    fetchRewardType();
    fetchRewardDetail();
  }, []);

  const fetchRewardDetail = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/reward-detail/story/${rewardId}`
      );
      setTotInfo(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchRewardType = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/reward/rewardcheckout/checktype/${rewardId}/${rewardTypeId}`
      );
      setRewardType(response.data);
      setPaymentAmount(response.data?.rewardAmount || 0);
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const combinedCardNum =
      cardNum1.trim() +
      "-" +
      cardNum2.trim() +
      "-" +
      cardNum3.trim() +
      "-" +
      cardNum4.trim();

    const combinedCardEndDate = cardEndDate1.trim() + "/" + cardEndDate2.trim();

    const paymentData = {
      user: {
        id: 1,
      },
      cardnumber: combinedCardNum,
      cardpassword: cardPw,
      payenddate: combinedCardEndDate,
      payperiod: period,
      birthday: birthday,
      shippingadress: address,
      shippingaddressdesc: addressDesc,
      rewardType: {
        id: rewardType ? rewardType.id : null,
        rewardAmount: rewardType ? rewardType.rewardAmount : 0,
      },
      paymentamount: paymentamount,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/payment/create",
        paymentData
      );
      console.log(response.data);
      navigate("/reward-checkout/complete");
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="checkout">
      <div className="checkout_header">{totInfo && totInfo.projName}</div>
      <div className="checkout_processes">
        <div className="checkout_process active">
          <CreditCardOutlined className="card" />
        </div>
        <div className="checkout_line"></div>
        <div className="checkout_process">
          <ReconciliationOutlined className="card" />
        </div>
      </div>
      <div className="checkout_process_texts">
        <div className="checkout_process_text active">카드/배송정보</div>
        <div></div>
        <div className="checkout_process_text">결제 예약 확인</div>
      </div>

      <div className="reward_list_titles">
        <div className="reward_list_title">내가 선택한 리워드</div>
      </div>
      <div className="rew_category_card">
        <h1 className="rew_price">
          {rewardType && rewardType.rewardAmount.toLocaleString()}원
        </h1>
        <div className="rew_sub_contents">
          {rewardType && rewardType.rewardAvailableLimit ? "제한" : "무제한"} |{" "}
          {rewardType && rewardType.rewardAvailableCount}개 펀딩
        </div>
        <div className="right">
          <CaretRightOutlined />
        </div>
        <div className="rew_content">
          {rewardType && rewardType.rewardTitle}
        </div>
        <div className="rew_content_info">
          {rewardType && rewardType.rewardContent}
        </div>
        {/* <div className="rew_content_checkoption">
          <select className="rew_content_select">
            <option value="s">S</option>
            <option value="m">M</option>
            <option value="l">L</option>
            <option value="xl">XL</option>
          </select>
        </div> */}
      </div>
      <form className="checkout_form" onSubmit={handleSubmit}>
        <p className="title">결제자 핸드폰 번호</p>
        <div className="phone">
          <input type="text" className="phone_num" required />
        </div>
        <p className="title">결제정보 입력</p>
        <div className="card_number">
          <input
            type="text"
            className="card_num"
            id="card_num1"
            name="cardNum1"
            value={cardNum1}
            onChange={(e) => setCardNum1(e.target.value)}
            required
          />
          <input
            type="text"
            className="card_num"
            id="card_num2"
            name="cardNum2"
            value={cardNum2}
            onChange={(e) => setCardNum2(e.target.value)}
            required
          />
          <input
            type="password"
            className="card_num"
            id="card_num3"
            name="cardNum3"
            value={cardNum3}
            onChange={(e) => setCardNum3(e.target.value)}
            required
          />
          <input
            type="password"
            className="card_num"
            id="card_num4"
            name="cardNum4"
            value={cardNum4}
            onChange={(e) => setCardNum4(e.target.value)}
            required
          />
        </div>
        <p className="title">카드 비밀번호</p>
        <input
          type="password"
          className="card_pw"
          placeholder="앞 2자리"
          id="card_pw"
          name="cardPw"
          value={cardPw}
          onChange={(e) => setCardPw(e.target.value)}
          required
        />
        <p className="title">유효기간</p>
        <div className="card_date">
          <input
            type="text"
            className="card_date_yy_mm"
            placeholder="MM"
            id="card_end_date1"
            name="cardEndDate"
            value={cardEndDate1}
            onChange={(e) => setCardEndDate1(e.target.value)}
            required
          />
          <input
            type="text"
            className="card_date_yy_mm"
            placeholder="YY"
            id="card_end_date2"
            name="cardEndDate"
            value={cardEndDate2}
            onChange={(e) => setCardEndDate2(e.target.value)}
            required
          />
        </div>
        <p className="title">생년월일</p>
        <input
          type="text"
          className="birthday"
          placeholder="법인 카드의 경우 사업자 등록번호 10자리 숫자"
          id="birthday"
          name="birthday"
          value={birthday}
          onChange={(e) => setBirthday(e.target.value)}
          required
        />

        <p className="title">할부기간</p>
        <select
          className="period"
          name="period"
          value={period}
          onChange={(e) => setPeriod(e.target.value)}
        >
          <option value="일시불">일시불</option>
          <option value="할부">할부</option>
        </select>

        <p className="title">주소</p>
        <div className="address_input">
          <input
            type="text"
            className="address"
            placeholder="배송지 주소"
            required
            name="address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
          <br />
          <input
            type="text"
            className="addressdesc"
            placeholder="배송지 상세 주소"
            name="addressDesc"
            value={addressDesc}
            onChange={(e) => setAddressDesc(e.target.value)}
            required
          />
        </div>

        <p className="sub_title">정보 동의</p>
        <div className="check_box">
          <label className="check_title">
            <input type="checkbox" className="check" required /> [필수] 결제에
            필요한 정보 제공에 동의합니다.
          </label>
          <br />
          <label className="check_title">
            <input type="checkbox" className="check" required /> [필수] 리워드
            제공에 필요한 정보 제공에 동의합니다.
          </label>
        </div>
        <button type="submit" className="complete">
          결제 예약 완료
        </button>
      </form>
    </div>
  );
};

export default Checkout;
