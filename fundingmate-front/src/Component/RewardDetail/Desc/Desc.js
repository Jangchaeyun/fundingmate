import React, { useState } from "react";
import "../../../pages/Rewarddetail/Rewarddetail.css";
import { useNavigate } from "react-router-dom";

const Desc = () => {
  const [imageSrc, setImageSrc] = useState("/assets/imgs/bracelet.jpg");
  const [isClicked, setIsClicked] = useState(false);
  let navigate = useNavigate();

  const handleClick = () => {
    if (isClicked) {
      setImageSrc("/assets/imgs/bracelet.jpg");
      setIsClicked(false);
    } else {
      setImageSrc("/assets/imgs/bracelet2.jpg");
      setIsClicked(true);
    }
  };
  return (
    <div className="desc">
      <div className="desc_subtitle">
        해당 프로젝트는<b>[HandMade]</b>와 함께합니다.
      </div>
      <div className="desc_title">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
      <div className="desc_contents">
        <div className="desc_img">
          <img src={imageSrc} className="main_img" />
          <img
            src="/assets/imgs/bracelet.jpg"
            className="sub_img1"
            onClick={handleClick}
          />
          <img
            src="/assets/imgs/bracelet2.jpg"
            className="sub_img2"
            onClick={handleClick}
          />
        </div>
        <div className="desc_content">
          <div className="fund_category">리워드</div>
          <div className="fund_price">
            12,345원 <b className="rewarding">펀딩중</b>
          </div>
          <div className="fund_rate">
            <div className="fund_rate_title">달성률</div>
            <div className="fund_rate_per">1050%</div>
            <sub className="fund_rate_price">목표 금액 10,0000원</sub>
          </div>
          <div className="fund_date">
            <div className="fund_date_title">남은기간</div>
            <div className="fund_date_dday">11일</div>
            <sub className="fund_date_end">2023.07.13 종료</sub>
          </div>
          <div className="fund_people">
            <div className="fund_people_title">참여자수</div>
            <div className="fund_people_count">140명</div>
            <button
              className="fund_btn"
              onClick={() => {
                navigate("/fund-checkout/fundpeople");
              }}
            >
              펀딩하기
            </button>
          </div>
          <button className="proj_share">프로젝트 공유하기</button>
          <div className="circles">
            <div className="circle">
              <img src="/assets/imgs/calendar.png" className="icon_img" />
            </div>
            <div className="circle">
              <img src="/assets/imgs/credit.png" className="icon_img" />
            </div>
            <div className="circle">
              <img src="/assets/imgs/delivery.png" className="icon_img" />
            </div>
          </div>
          <div className="schedule">
            <div className="fund_end_date_title">펀딩 종료일</div>
            <div className="price_date_title">결제 예정일</div>
            <div className="send_date_title">발송 예정일</div>
          </div>
          <div className="schedule">
            <div className="end">23/07/13</div>
            <div className="end">23/07/22</div>
            <div className="end">23/07/23</div>
          </div>
          <div className="company">
            <div className="name_view">
              <img src="/assets/imgs/smartboy.jpg" className="boy_img" />
              <div className="company_name">스마트보이</div>
            </div>
          </div>
          <div className="info">
            펀딩을 마치면 결제 예약 상태입니다. 종료일에 100% 이상이 달성되었을
            경우에만 결제 예정일에 결제가 됩니다.
          </div>
        </div>
      </div>
    </div>
  );
};

export default Desc;
