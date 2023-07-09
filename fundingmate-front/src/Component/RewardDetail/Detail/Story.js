import React, { useEffect, useState } from "react";
import "../../../pages/Rewarddetail/Rewarddetail.css";
import { Link, useParams } from "react-router-dom";
import Desc from "../Desc/Desc";
import axios from "axios";

const Story = () => {
  const [reward, setReward] = useState({
    id: 0,
    projName: "",
    projTargetAmout: 0,
    projDateStart: null,
    projDateEnd: null,
    rewardRepImgSavedName: "",
    projKeyWord: "",
    rewardVideoAddress: "",
    rewardContentImgSavedName: "",
    projContent: "",
    rewardRefundExchangePolicy: "",
    rewardContact: "",
    rewardEmail: "",
    rewardCategory: "",
    modelName: "",
    countryOfOrigin: "",
    manufacturer: "",
    asPhoneNumber: "",
    rewardIdBusinessLicenseImgSavedName: "",
    businessAddress: "",
    bank: "",
    accNumber: "",
    depositorName: "",
    rewardBankAccountCopyImgSavedName: "",
    taxBillEmail: "",
    websiteUrl: "",
    facebookUrl: "",
    instagramUrl: "",
    blogUrl: "",
    twitterUrl: "",
    user: null,
    rewardTypes: [],
  });

  const { rewardId } = useParams();

  useEffect(() => {
    axios
      .get(`http://localhost:8090/reward-detail/story/${rewardId}`)
      .then((res) => {
        setReward(res.data.reward);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div className="desc">
      <Desc reward={reward} />
      <div className="menu">
        <hr />
        <div className="menu_items">
          <Link className="story active" to={"/reward-detail/story"}>
            스토리
          </Link>
          <div className="contact_box">
            <Link className="contact" to={"/reward-detail/contact"}>
              문의
            </Link>
            <div className="contact_count">0</div>
          </div>
          <Link className="guide" to={"/reward-detail/guide"}>
            안내
          </Link>
        </div>
      </div>
      <div className="story_content">
        {reward.projContent}
        <div className="product_img">
          <img src={reward.rewardContentImgSavedName} className="images" />
        </div>
      </div>
    </div>
  );
};

export default Story;
