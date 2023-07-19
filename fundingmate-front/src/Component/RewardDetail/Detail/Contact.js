import React, { useEffect, useState } from "react";
import "../../../pages/Rewarddetail/Rewarddetail.css";
import Desc from "../Desc/Desc";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { DeleteOutlined } from "@ant-design/icons";
import { useSelector } from "react-redux";

const Contact = () => {
  const { rewardId } = useParams();
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
  const [rewardComments, setRewardComments] = useState([]);
  const [viewDesc, setViewDesc] = useState(false);
  const [inquiryText, setInquiryText] = useState("");
  const loggedInUser = 1;
  const [replyText, setReplyText] = useState({});
  const [replyData, setReplyData] = useState({});
  const [totalPaymentAmounts, setTotalPaymentAmounts] = useState({});
  const [personCount, setPersonCount] = useState(0);

  const submitInquiry = () => {
    const requestBody = {
      comContent: inquiryText,
      reward: {
        id: reward.id,
      },
      user: {
        id: loggedInUser,
      },
    };

    axios
      .post(
        `http://localhost:8090/reward-detail/contact/${rewardId}`,
        requestBody
      )
      .then((res) => {
        console.log(res.data);
        setInquiryText("");
        setRewardComments(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deleteComment = (commentId) => {
    axios
      .delete(
        `http://localhost:8090/reward-detail/contact/comment/${commentId}`
      )
      .then((res) => {
        console.log(res.data);
        setRewardComments(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const submitReply = (commentId) => {
    const requestBody = {
      repContent: replyText[commentId],
      rewardId: reward.id,
      commentId: commentId,
      userId: loggedInUser,
    };

    if (reward.user.id === loggedInUser) {
      axios
        .post(
          `http://localhost:8090/reward-detail/contact/comment/reply`,
          requestBody
        )
        .then((res) => {
          console.log(res.data);
          setReplyText((prevReplyText) => ({
            ...prevReplyText,
            [commentId]: "",
          }));
          setRewardComments(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      console.log("You are not authorized to reply to this comment.");
    }
  };

  const fetchCommentReplies = (commentId) => {
    axios
      .get(
        `http://localhost:8090/reward-detail/contact/comment/reply/${commentId}`
      )
      .then((res) => {
        setReplyData((prevReplyData) => ({
          ...prevReplyData,
          [commentId]: res.data,
        }));
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const storyResponse = await axios.get(
          `http://localhost:8090/reward-detail/story/${rewardId}`
        );
        console.log(storyResponse.data);
        setReward(storyResponse.data.reward);
        setViewDesc(true);

        const contactResponse = await axios.get(
          `http://localhost:8090/reward-detail/contact/${rewardId}`
        );
        console.log(contactResponse.data);
        setRewardComments(contactResponse.data);
        contactResponse.data.forEach((comment) => {
          fetchCommentReplies(comment.id);
        });
      } catch (error) {
        console.log(error);
      }
    };
    fetchParticipantCount();
    fetchData();
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
      {viewDesc && (
        <Desc
          reward={reward}
          totalPaymentAmount={totalPaymentAmounts[reward.id] || 0}
          personCount={personCount}
        />
      )}
      <div className="menu">
        <hr />
        <div className="menu_items">
          <Link className="story" to={`/reward-detail/story/${rewardId}`}>
            스토리
          </Link>
          <div className="contact_box">
            <Link
              className="contact active"
              to={`/reward-detail/contact/${rewardId}`}
            >
              문의
            </Link>
            <div className="contact_count">0</div>
          </div>
          <Link className="guide" to={`/reward-detail/guide/${rewardId}`}>
            안내
          </Link>
        </div>
      </div>
      <div className="con">
        <p className="con_subtitle">
          여러분들이 궁금한 모든 것들을 편하게 질문해주세요!!!
        </p>
        <p className="con_title">문의 작성 시 유의사항</p>
        <li className="con_info">
          리워드 관련 문의는 댓글에 달아주시면 정확한 답변을 받을 수 있습니다.
        </li>
        <textarea
          className="con_input"
          value={inquiryText}
          onChange={(e) => setInquiryText(e.target.value)}
        />
        <button type="submit" className="sub" onClick={submitInquiry}>
          문의하기
        </button>

        {rewardComments.length > 0 ? (
          rewardComments.map((comment) => (
            <div className="reward_con_list" key={comment.id}>
              <div className="reward_con_name">
                {comment.user && comment.user.name} | {""}
                {comment.comRegistrationDate}
              </div>
              {comment.user && comment.user.id === loggedInUser && (
                <div
                  className="del_sub"
                  onClick={() => deleteComment(comment.id)}
                >
                  <DeleteOutlined className="delete" />
                </div>
              )}
              <div className="reward_con_content">{comment.comContent}</div>
              {reward.user && reward.user.id === loggedInUser && (
                <div className="reward_reply_content">
                  <input
                    type="text"
                    className="reply_text"
                    value={replyText[comment.id] || ""}
                    onChange={(e) =>
                      setReplyText((prevReplyText) => ({
                        ...prevReplyText,
                        [comment.id]: e.target.value,
                      }))
                    }
                  />
                  <button
                    className="reply_submit"
                    onClick={() => submitReply(comment.id)}
                  >
                    답장
                  </button>
                </div>
              )}
              {replyData[comment.id]?.map((reply) => (
                <div key={reply.id}>
                  <div className="reply_reward_con_name">
                    {reward.user.name} | {reply.repRegisterationDate}
                  </div>
                  <div className="reply_reward_con_content">
                    {reply.repContent}
                  </div>
                </div>
              ))}
            </div>
          ))
        ) : (
          <div className="con_list">등록된 문의가 없습니다.</div>
        )}
      </div>
    </div>
  );
};

export default Contact;
