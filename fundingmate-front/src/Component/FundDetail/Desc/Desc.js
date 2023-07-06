import React, { useState } from "react";
import "../../../pages/Funddetail/Funddetail.css";
import { useNavigate } from "react-router-dom";
import CompanyModel from "../../Company/CompanyModel";

const Desc = () => {
  const [imageSrc, setImageSrc] = useState("/assets/imgs/bracelet.jpg");
  const [isClicked, setIsClicked] = useState(false);
  const [isModalVisible, setIsModalVisible] = useState(false);
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

  const handleCompanyClick = () => {
    setIsModalVisible(true);
  };

  return (
    <div className="desc">
      <div className="desc_subtitle"></div>
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
          <div className="fund_category">악세서리</div>
          <div className="fund_price">
            21,345,455원 <b className="rewarding">펀딩중</b>
          </div>
          <div className="fund_rate">
            <div className="fund_rate_title">달성률</div>
            <div className="fund_rate_per">1050%</div>
            <sub className="fund_rate_price">목표 금액 10,0000원</sub>
          </div>
          <div className="fund_date">
            <div className="fund_date_title">남은기간</div>
            <div className="fund_date_dday">11일</div>
            <sub className="fund_date_enddate">2023.07.13 종료</sub>
          </div>
          <div className="fund_people">
            <div className="fund_people_title">펀딩자수</div>
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
          <div className="proj_good_share">
            <button className="proj_good">12명이 관심있어요</button>
            <button className="fund_proj_share">공유하기</button>
          </div>
          <div className="company" onClick={handleCompanyClick}>
            <div className="name_view">
              <img src="/assets/imgs/smartboy.jpg" className="boy_img" />
              <div className="company_name">스마트보이</div>
            </div>
          </div>
          <div className="info">수익배분은 3%</div>
        </div>
      </div>
      {isModalVisible && (
        <CompanyModel onClose={() => setIsModalVisible(false)} />
      )}
    </div>
  );
};

export default Desc;
