import React, { useEffect, useState } from "react";
import Desc from "../Desc/Desc";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { ca } from "date-fns/locale";
import Header from "../../Header/Header";
import Footer from "../../Footer/Footer";

const FundStory = () => {
  const [viewDesc, setViewDesc] = useState(false);
  const [totalPayment, setTotalPaymentAmounts] = useState([]);
  const [totInfo, setTotInfo] = useState();
  const [personCount, setPersonCount] = useState(0);
  const { investId } = useParams();

  useEffect(() => {
    console.log("Fetching invest data for investId:", investId);
    if (investId) {
      axios
        .get(`http://localhost:8080/invest-detail/story/${investId}`)
        .then((res) => {
          console.log(res.data);
          setTotInfo(res.data);
          setViewDesc(true);
        })
        .catch((err) => {
          console.log(err);
          setViewDesc(false);
        });

      fetchParticipantCount();
    }
  }, [investId]);

  const fetchTotalPaymentAmounts = async () => {
    try {
      if (totInfo && totInfo.id) {
        const response = await axios.get(
          `http://localhost:8080/payment/total-amount-same-invests?investIds=${totInfo.id}`
        );
        const totalAmounts = response.data;
        setTotalPaymentAmounts(totalAmounts);
      }
    } catch (error) {
      console.log("Error fetching total payment amounts: ", error);
    }
  };

  const fetchParticipantCount = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/person-count/${investId}`
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
  }, [totInfo]);
  return (
    <>
      <Header />
      <div className="desc">
        <Desc />
        <div className="menu">
          <hr />
          <div className="menu_items">
            <Link className="fund_story active" to={"/fund-detail/story"}>
              스토리
            </Link>
            <div className="contact_box">
              <Link className="fund_contact" to={"/fund-detail/contact"}>
                문의
              </Link>
              <div className="contact_count">0</div>
            </div>
            <Link className="fund_guide" to={"/fund-detail/guide"}>
              안내
            </Link>
          </div>
        </div>
        <div className="fund_story_content">창업 아이템 소개</div>
        <div className="fund_story_content_text"></div>
        <div className="fund_story_content">창업 아이템의 사업성</div>
        <div className="fund_story_content_text"></div>
        <div className="fund_story_content">창업 아이템 의 가치</div>
        <div className="fund_story_content_text"></div>
        <div className="fund_story_content">창업 아이템 의 기대효과</div>
        <div className="fund_story_content_text"></div>
      </div>
      <Footer />
    </>
  );
};

export default FundStory;
