import React, { useEffect, useState } from "react";
import "../../pages/Checkout/Checkout.css";
import { CaretRightOutlined } from "@ant-design/icons";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const CheckCategory = () => {
  let navigate = useNavigate();
  let { rewardId } = useParams();
  const [rewardTypes, setRewardTypes] = useState([]);
  const [totInfo, setTotInfo] = useState();

  useEffect(() => {
    fetchRewardDetail();
    fetchRewardTypes();
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

  const fetchRewardTypes = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/reward/rewardcheckout/check/${rewardId}`
      );
      setRewardTypes(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const handleCheckoutClick = (rewardTypeId) => {
    navigate(`/reward-checkout/${rewardId}/${rewardTypeId}`);
  };

  return (
    <div className="rew_check_category checkout">
      <div className="rew_check_header">{totInfo && totInfo.projName}</div>
      <div className="rew_check_info">펀딩을 마치면 결제 예약 상태입니다.</div>

      <h1 className="rew_title">리워드 선택</h1>
      {rewardTypes.map((rewardType) => (
        <div className="rew_category_card" key={rewardType.id}>
          <h1 className="rew_price">
            {rewardType.rewardAmount.toLocaleString()}원
          </h1>
          <div className="rew_sub_content">
            <div className="rew_count">
              {rewardType.rewardAvailableLimit ? "제한" : "무제한"} |
              {rewardType.rewardAvailableCount}개 펀딩
            </div>
            <div className="rew_date">
              예상 배송일 {rewardType.deliveryDate}
            </div>
          </div>
          <div
            className="right"
            onClick={() => handleCheckoutClick(rewardType.id)}
          >
            <CaretRightOutlined />
          </div>
          <div className="rew_content">{rewardType.rewardTitle}</div>
          <div className="rew_content_info">{rewardType.rewardContent}</div>
          {/* <div className="rew_content_checkoption">
          <select className="rew_content_select">
            <option value="s">S</option>
            <option value="m">M</option>
            <option value="l">L</option>
            <option value="xl">XL</option>
          </select>
      </div> */}
        </div>
      ))}
    </div>
  );
};

export default CheckCategory;
