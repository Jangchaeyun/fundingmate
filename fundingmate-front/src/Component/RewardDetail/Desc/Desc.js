import React, { useEffect, useState } from "react";
import "../../../pages/Rewarddetail/Rewarddetail.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import CompanyModel from "../../Company/CompanyModel";
import { useParams } from "react-router";
import moment from "moment";

const Desc = ({
  reward,
  totalPaymentAmount,
  personCount,
  rewardContentImgSavedName,
}) => {
  const [imageSrc, setImageSrc] = useState(reward.rewardRepImgSavedName);
  const [isModalVisible, setIsModalVisible] = useState(false);

  const getRemainingDays = () => {
    const endDate = moment(reward?.projDateEnd);
    const today = moment();
    const remainingDays = endDate.diff(today, "days");
    return remainingDays;
  };

  let navigate = useNavigate();

  const handleCompanyClick = () => {
    setIsModalVisible(true);
  };

  console.log("AAA");
  console.log(reward.rewardContentImgSavedName);

  const hasProjDateEndPassed = moment(reward?.projDateEnd).isBefore(moment());

  return (
    <div className="desc">
      <div className="desc_title">{reward.projName}</div>
      <div className="desc_contents">
        <div className="desc_img">
          <img
            src={`http://localhost:8080/img/${imageSrc}`}
            className="main_img"
          />
          <img
            src={`http://localhost:8080/img/${reward.rewardRepImgSavedName}`}
            className="sub_img1"
            id={reward.rewardRepImgSavedName.fileName}
            onClick={(e) => setImageSrc(reward.rewardRepImgSavedName)}
          />
          {Array.isArray(reward.rewardContentImgSavedName) &&
            reward.rewardContentImgSavedName.map((filename, index) => (
              <img
                key={index}
                src={`http://localhost:8080/img/${filename}`}
                className="sub_img2"
                id={filename}
                onClick={(e) => setImageSrc(filename)}
              />
            ))}
        </div>
        <div className="desc_content">
          <div className="fund_category">리워드</div>
          <div className="fund_price">
            {totalPaymentAmount.toLocaleString()}원&nbsp;
            <b className="rewarding">펀딩중</b>
          </div>
          <div className="fund_rate">
            <div className="fund_rate_title">달성률</div>
            <div className="fund_rate_per">
              {((totalPaymentAmount / reward.projTargetAmount) * 100).toFixed(
                1
              )}
              %
            </div>
            <sub className="fund_rate_price">
              목표 금액 {reward.projTargetAmount.toLocaleString()}원
            </sub>
          </div>
          <div className="fund_date">
            <div className="fund_date_title">남은기간</div>
            {hasProjDateEndPassed ? (
              <div className="fund_date_dday">펀딩 종료</div>
            ) : (
              <>
                <div className="fund_date_dday">{getRemainingDays() + 1}일</div>
                <sub className="fund_date_end">{reward.projDateEnd} 종료</sub>
              </>
            )}
          </div>
          <div className="fund_people">
            <div className="fund_people_title">참여자수</div>
            <div className="fund_people_count">
              {personCount !== undefined ? (
                <div className="fund_people_count">{personCount}명</div>
              ) : (
                <div className="fund_people_count">Loading...</div>
              )}
            </div>
            <button
              className={hasProjDateEndPassed ? "funding-closed" : "fund_btn"}
              onClick={() => {
                navigate(`/checkout/check/${reward.id}`);
              }}
            >
              {hasProjDateEndPassed ? "펀딩 종료" : "펀딩하기"}
            </button>
          </div>
          <button className="proj_share">프로젝트 공유하기</button>
          <div className="circles">
            <div className="circle1">
              <img src="/assets/imgs/calendar.png" className="icon_img" />
            </div>
            <div className="circle2">
              <img src="/assets/imgs/credit.png" className="icon_img" />
            </div>
            <div className="circle3">
              <img src="/assets/imgs/delivery.png" className="icon_img" />
            </div>
          </div>
          <div className="schedule">
            <div className="fund_end_date_title">펀딩 종료일</div>
            <div className="price_date_title">결제 예정일</div>
            <div className="send_date_title">발송 예정일</div>
          </div>
          <div className="schedule">
            <div className="end1">{reward.projDateEnd}</div>
            <div className="end2">
              {moment(reward.projDateEnd).add(1, "day").format("YYYY-MM-DD")}
            </div>
            <div className="end3">{reward.rewardTypes[0].deliveryDate}</div>
          </div>
          <div className="company" onClick={handleCompanyClick}>
            <div className="name_view">
              <img src="/assets/imgs/people.png" className="people_img" />
              <div className="company_name">{reward.manufacturer}</div>
            </div>
          </div>
          <div className="info">
            펀딩을 마치면 결제 예약 상태입니다. 종료일에 100% 이상이 달성되었을
            경우에만 결제 예정일에 결제가 됩니다.
          </div>
        </div>
      </div>
      {isModalVisible && (
        <CompanyModel
          reward={reward}
          onClose={() => setIsModalVisible(false)}
        />
      )}
    </div>
  );
};

export default Desc;
