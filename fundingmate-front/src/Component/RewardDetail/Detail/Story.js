import React, { useEffect, useState } from "react";
import "../../../pages/Rewarddetail/Rewarddetail.css";
import { Link, useParams } from "react-router-dom";
import Desc from "../Desc/Desc";
import axios from "axios";

const Story = () => {
  const [viewDesc, setViewDesc] = useState(false);
  const [totalPaymentAmounts, setTotalPaymentAmounts] = useState({});
  const [reward, setReward] = useState({
    id: 0,
    projName: "",
    projTargetAmount: 0,
    projDateStart: null,
    projDateEnd: null,
    deliveryDate: null,
    repFile: null,
    projKeyword: "",
    rewardVideoAddress: "",
    conFile: null,
    projContent: "",
    rewardRefundExchangePolicy: "",
    rewardContact: "",
    rewardEmail: "",
    rewardCategory: "",
    modelName: "",
    countryOfOrigin: "",
    manufacturer: "",
    rewardLaw: "",
    asPhoneNumber: "",
    businessImg: null,
    businessAddress: "",
    bank: "",
    accNumber: "",
    depositorName: "",
    bankImg: null,
    taxBillEmail: "",
    websiteUrl: "",
    facebookUrl: "",
    instagramUrl: "",
    blogUrl: "",
    twitterUrl: "",
    user: null,
    rewardTypes: [],
  });
  const [personCount, setPersonCount] = useState(0);
  const { rewardId } = useParams();

  useEffect(() => {
    axios
      .get(`http://localhost:8090/reward-detail/story/${rewardId}`)
      .then((res) => {
        console.log(res.data);
        setReward(res.data.reward);
        setViewDesc(true);
      })
      .catch((err) => {
        console.log(err);
      });

    fetchParticipantCount();
  }, [rewardId]);

  const fetchTotalPaymentAmounts = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8090/payment/total-amount-same-rewards?rewardIds=${reward.id}`
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
        `http://localhost:8090/person-count/${rewardId}`
      );
      const count = response.data;
      setPersonCount(count);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    if (reward && reward.id) {
      fetchTotalPaymentAmounts();
    }
  }, [reward, totalPaymentAmounts]);

  return (
    <div className="desc">
      {viewDesc && reward && (
        <Desc
          reward={reward}
          totalPaymentAmount={totalPaymentAmounts[reward.id] || 0}
          personCount={personCount}
        />
      )}
      <div className="menu">
        <hr className="menu_hr" />
        <div className="menu_items">
          <Link
            className="story active"
            to={`/reward-detail/story/${rewardId}`}
          >
            스토리
          </Link>
          <div className="contact_box">
            <Link className="contact" to={`/reward-detail/contact/${rewardId}`}>
              문의
            </Link>
            <div className="contact_count">0</div>
          </div>
          <Link className="guide" to={`/reward-detail/guide/${rewardId}`}>
            안내
          </Link>
        </div>
      </div>
      <div className="story_content">
        {reward.projContent}
        <div className="product_img">
          {reward.conFile && (
            <img
              src={`http://localhost:8090/img/${reward.conFile.fileName}`}
              className="images"
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default Story;
