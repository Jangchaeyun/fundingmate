import React from "react";
import "../../pages/Checkout/Checkout.css";
import { useNavigate } from "react-router-dom";
import FundCategory from "./FundCategory";

const Checkfund = () => {
  let navigate = useNavigate();
  return (
    <div className="fund_checkout_boxs">
      <div className="fund_checkout_header">
        실버 커팅볼 스퀘어 체인 여자 팔찌
      </div>
      <h1 className="fund_box_title">펀딩 선택</h1>
      <FundCategory />
    </div>
  );
};

export default Checkfund;
