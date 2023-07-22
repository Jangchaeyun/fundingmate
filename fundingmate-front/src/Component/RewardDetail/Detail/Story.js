import React, { useEffect, useState } from "react";
import "../../../pages/Rewarddetail/Rewarddetail.css";
import { Link, useParams } from "react-router-dom";
import Desc from "../Desc/Desc";
import axios from "axios";

const Story = () => {
  const [viewDesc, setViewDesc] = useState(false);
  const [totalPaymentAmounts, setTotalPaymentAmounts] = useState({});
  const [totInfo, setTotInfo] = useState();
  const [personCount, setPersonCount] = useState(0);

  const { rewardId } = useParams();

  useEffect(() => {
    console.log("Fetching reward data for rewardId:", rewardId);
    if (rewardId) {
      axios
        .get(`http://localhost:8080/reward-detail/story/${rewardId}`)
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
  }, [rewardId]);

  const fetchTotalPaymentAmounts = async () => {
    try {
      if (totInfo && totInfo.id) {
        const response = await axios.get(
          `http://localhost:8080/payment/total-amount-same-rewards?rewardIds=${totInfo.id}`
        );
        const totalAmounts = response.data;
        setTotalPaymentAmounts(totalAmounts);
      }
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
  }, [totInfo]);

  return (
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
        {totInfo && totInfo.projContent && totInfo.projContent.trim() !== "" ? (
          <div dangerouslySetInnerHTML={{ __html: totInfo.projContent }} />
        ) : (
          <div>No content available</div>
        )}
        <div className="product_img">
          {totInfo?.rewardContentImgSavedName?.length > 0 &&
            totInfo.rewardContentImgSavedName.map((img, index) => (
              <img
                key={index}
                src={`http://localhost:8080/img/${img.fileName}`}
                className="images"
              />
            ))}
        </div>
      </div>
    </div>
  );
};

export default Story;
