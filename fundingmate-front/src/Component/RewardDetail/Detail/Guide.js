import React, { useEffect, useState } from "react";
import "../../../pages/Rewarddetail/Rewarddetail.css";
import Desc from "../Desc/Desc";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import Header from "../../Header/Header";
import CorFooter from "../../Footer/CorFooter";
import Footer from "../../Footer/Footer";

const Guide = () => {
  const { rewardId } = useParams();
  const [totInfo, setTotInfo] = useState();
  const [viewDesc, setViewDesc] = useState(false);
  const [totalPaymentAmounts, setTotalPaymentAmounts] = useState({});
  const [personCount, setPersonCount] = useState(0);

  useEffect(() => {
    const fetchReward = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/reward-detail/guide/${rewardId}`
        );
        console.log(response.data);
        setTotInfo(response.data);
        setViewDesc(true);
      } catch (error) {
        console.log(error);
      }
    };
    fetchParticipantCount();
    fetchReward();
  }, [rewardId]);

  const fetchTotalPaymentAmounts = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/payment/total-amount-same-rewards?rewardIds=${totInfo.id}`
      );
      const totalAmounts = response.data;
      setTotalPaymentAmounts(totalAmounts);
    } catch (error) {
      console.error("Error fetching total payment amounts:", error);
    }
  };

  const fetchParticipantCount = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/person-count/${rewardId}`
      );
      const count = response.data;
      setPersonCount(count);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    if (totInfo && totInfo.id) {
      fetchTotalPaymentAmounts();
    }
  }, [totInfo, totalPaymentAmounts]);

  return (
    <>
      <Header />
      <div className="desc">
        {viewDesc && totInfo && (
          <div>
            <Desc
              reward={totInfo}
              totalPaymentAmount={totalPaymentAmounts[totInfo.id] || 0}
              personCount={personCount}
            />
          </div>
        )}
        <div className="menu">
          <hr />
          <div className="menu_items">
            <Link
              className="story"
              to={`${window.location.origin}/rewarddetail/story/${rewardId}`}
            >
              스토리
            </Link>
            <div className="contact_box">
              <Link
                className="contact"
                to={`${window.location.origin}/rewarddetail/contact/${rewardId}`}
              >
                문의
              </Link>
              <div className="contact_count">0</div>
            </div>
            <Link
              className="guide active"
              to={`${window.location.origin}/rewarddetail/guide/${rewardId}`}
            >
              안내
            </Link>
          </div>
        </div>
        <div className="guide_infos">
          {totInfo && (
            <>
              <div className="guide_title">진행자 교환 및 환불 정책</div>
              <p className="guide_content">
                {totInfo.rewardRefundExchangePolicy}
              </p>
              <div className="guide_title">메이트 교환 및 환불 정책</div>
              <p className="guide_content">
                - 펀딩 취소는 프로젝트 종료 전까지만 마이페이지의 펀딩한
                프로젝트에서 할 수 있으며, 크라우드펀딩의 특성상 크라우디에서는
                프로젝트 종료 이후 단순 변심에 의한 펀딩 취소가 불가능합니다.
                <br />- 프로젝트 종료 이후의 AS, 교환 및 환불에 관한 문의는
                진행자의 교환 및 환불 정책을 따르거나 진행자의 연락처로 문의해야
                합니다.
              </p>
              <div className="guide_title">유의사항</div>
              <p className="guide_content">{totInfo.rewardLaw}</p>
            </>
          )}
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Guide;
