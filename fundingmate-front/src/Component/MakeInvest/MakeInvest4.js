import React from "react";
import { Route, useNavigate, useLocation } from "react-router-dom";
import "./MakeInvest4.css";
import "./MakeInvestCommon.css";
import {
  PlusOutlined,
  PlusSquareOutlined,
  MinusSquareOutlined
} from "@ant-design/icons";
import { Button, Modal } from "antd";
import { useState } from "react";
import { DatePicker } from "antd";
import "dayjs/locale/zh-cn";
import dayjs from "dayjs";
import { nanoid } from "nanoid";
import axios from "axios";
import Footer from "../../Component/Footer/Footer";
import Header from "../../Component/Header/Header";
const MakeInvest4 = () => {
  const location = useLocation();
  const preTotInfo = location.state.totInfo;
  const [totInfo, setTotInfo] = useState(preTotInfo);

  const today = new Date();
  const todayDate =
    today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();

  const [modalOpen, setModalOpen] = useState(false);
  const initCard = {
    investAmount: "",
    investLimit: "",
    investLimitCount: 0
  };
  const [card, setCard] = useState({ ...initCard });
  // console.log("card.options.length:"+card.options.length);
  const [cards, setCards] = useState([]); // 카드 배열 추가
  const [editingCardIndex, setEditingCardIndex] = useState(null); // 수정 중인 카드의 인덱스

  const handleOk = () => {
    setModalOpen(false);
    if (editingCardIndex != null) {
      //수정
      totInfo.cards[editingCardIndex] = { ...card };
    } else {
      //추가
      setTotInfo({ ...totInfo, cards: [...totInfo.cards, card] }); // 새로운 카드 객체를 배열에 추가
    }
    console.log(card);
  };

  const handleCancel = () => {
    setModalOpen(false);
  };

  const [limitClicked, setLimitClicked] = useState(null);
  const handleLimitButtonClick = (isLimit) => {
    setCard({ ...card, investLimit: isLimit });
    setLimitClicked(isLimit);
  };

  const [saClicked, setSaClicked] = useState(null);
  const handleSaButtonClick = (isAddress) => {
    setCard({ ...card, rewardShipAddress: isAddress });
    setSaClicked(isAddress);
  };

  const [showOption, setShowOption] = useState(false);
  console.log("showOption:" + showOption);

  const handleShowOptionButtonClick = () => {
    setCard({ ...card, options: [{ rewardOptName: "", rewardOptCon: "" }] });
    setShowOption(true);
  };
  const handleAddOption = () => {
    setCard({
      ...card,
      options: [
        ...card.options,
        { id: nanoid(), rewardOptName: "", rewardOptCon: "" }
      ]
    });
  };

  const handleDeleteOption = (index) => {
    let modOptions = card.options;
    modOptions.splice(index, 1);
    setCard({ ...card, options: [...modOptions] });
    console.log(modOptions.length);
    if (modOptions.length === 0) {
      setShowOption(false);
    }
  };
  const navigateToStep1 = useNavigate();
  const navigateToStep2 = useNavigate();

  const handlePreviousStep = () => {
    navigateToStep1("/makeInvestStory", { state: { totInfo: totInfo } });
  };

  //   const handleNextStep = () => {
  //     axios
  //       .post("/make-invest/typelist", totInfo) // 서버의 API 엔드포인트에 맞게 경로와 데이터를 수정해야 합니다.
  //       .then(() => {
  //         navigateToStep2("/make-invest/hostinfo", {
  //           state: { totInfo: totInfo },
  //         });
  //       })
  //       .catch((error) => {
  //         console.error(error);
  //       });
  //   };

  const handleNextStep = () => {
    //setTotInfo({...totInfo, cards:[...cards]})
    navigateToStep2("/makeInvestHostinfo", { state: { totInfo: totInfo } });
  };

  const rewardChange = (e) => {
    console.log(e);
    setCard({ ...card, [e.target.name]: e.target.value });
  };

  const handleCopyCard = (index) => {
    console.log(totInfo.cards);
    const copiedCard = { ...totInfo.cards[index] };
    console.log(copiedCard);
    setTotInfo({ ...totInfo, cards: [...totInfo.cards, copiedCard] });
  };

  const handleRewDelete = (index) => {
    setTotInfo((prevTotInfo) => {
      const updatedCards = [...prevTotInfo.cards];
      updatedCards.splice(index, 1);
      return { ...prevTotInfo, cards: updatedCards };
    });
  };

  const handleNumInputChange = (e) => {
    console.log(e);
    const inputValue = e.target.value;
    const numericValue = inputValue.replace(/\D/g, ""); // 숫자 이외의 문자 제거
    setCard({ ...card, [e.target.name]: numericValue });
  };

  return (
    <>
      <Header />
      <div className="investMake-wrapper">
        <div className="proj-progress-div">
          <div className="proj-progress proj-progress-common proj-progress-line">
            1
          </div>
          <div className="proj-progress proj-progress-common proj-progress-line">
            2
          </div>
          <div className="proj-progress proj-progress-common proj-progress-line">
            3
          </div>
          <div className="proj-progress-ing proj-progress-common proj-progress-line">
            4
          </div>
          <div className="proj-progress proj-progress-common">5</div>
        </div>

        <p className="custom-font-sub-title">
          <b>프로젝트 투자조건을 구성해주세요</b>
        </p>

        <p className="custom-font-text">
          프로젝트 시작을 위해서는{" "}
          <b style={{ color: "#E93232" }}>최소 1개 이상의 투자조건</b>이 있어야
          합니다.
        </p>

        <Button
          type="primary"
          className="invest-card-add-button"
          id="rew-card-add-button"
          icon={
            <PlusOutlined
              id="rew-card-add-icon"
              style={{ fontSize: "11px", marginRight: "1px" }}
            />
          }
          onClick={() => {
            setEditingCardIndex(null);
            setCard({ ...initCard });
            setLimitClicked(null);
            setSaClicked(null);
            setShowOption(false);
            setModalOpen(true);
          }}
        >
          투자조건 추가
        </Button>
        <Modal
          title={<p style={{ fontSize: "25px" }}>리워드 추가</p>}
          centered
          open={modalOpen}
          onOk={handleOk}
          onCancel={handleCancel}
          width={655}
          okText={editingCardIndex !== null ? "수정" : "추가"} //
          cancelText="취소"
          okButtonProps={{
            style: {
              backgroundColor: "var(--main-color)",
              fontFamily: "SUITE-Regular"
            }
          }}
          cancelButtonProps={{ id: "invest-modal-custom-cancel-button" }}
          id="makereward_modal"
        >
          <p className="custom-font-modal-sub-title">투자 금액</p>
          <input
            type="text"
            name="investAmount"
            id="rew-amount-input"
            className="modal-input-box"
            placeholder="0"
            value={card.investAmount}
            onChange={handleNumInputChange}
          />
          &nbsp;원
          <p className="custom-font-modal-sub-title">투자 제공 가능 수</p>
          <div style={{ display: "flex", alignItems: "center" }}>
            <div className="rew-limit-buttons">
              <button
                className={`rew-unlimited ${
                  card.investLimit === 0 ? "clicked" : ""
                }`}
                id="rew-unlimited-button"
                onClick={() => handleLimitButtonClick(0)}
              >
                무제한
              </button>
              <button
                className={`rew-limit ${
                  card.investLimit === 1 ? "clicked" : ""
                }`}
                id="rew-limit-button"
                onClick={() => handleLimitButtonClick(1)}
              >
                제한
              </button>
            </div>
            {limitClicked === 1 && (
              <div style={{ display: "flex", alignItems: "center" }}>
                <input
                  type="text"
                  name="investLimitCount"
                  className="modal-limit-input-box"
                  placeholder="0"
                  value={card.investLimitCount}
                  onChange={handleNumInputChange}
                />
                &nbsp;개
              </div>
            )}
          </div>
        </Modal>
        {totInfo.cards.map((cardItem, index) => (
          <div className="make-invest-card" key={index}>
            <div className="make-invest-card-div">
              <div>
                <div className="make-rew-card-price">
                  {cardItem.investAmount}원
                </div>
                <div className="make-rew-card-contents">
                  <div className="make-rew-card-count">
                    {cardItem.investLimit == 1 ? "제한" : "무제한"}
                    &nbsp;|&nbsp;0개 펀딩
                  </div>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                </div>
              </div>
              <div>
                <div className="make-rew-card-edit-button-div">
                  <button
                    className="make-rew-card-edit-button"
                    onClick={() => handleCopyCard(index)}
                  >
                    복사
                  </button>
                  <button
                    className="make-rew-card-edit-button"
                    onClick={() => {
                      setCard({ ...cardItem });
                      setEditingCardIndex(index); // 수정 중인 카드의 인덱스 저장
                      setModalOpen(true); // 모달 열기
                    }}
                  >
                    수정
                  </button>
                  <button
                    className="make-rew-card-edit-button make-rew-card-edit-button-delete"
                    onClick={() => handleRewDelete(index)}
                  >
                    삭제
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}

        <div className="button-top-margin"></div>
        <div className="investMake-button-div">
          <button
            className="investMake-prev-button"
            onClick={handlePreviousStep}
          >
            {" "}
            이전 단계{" "}
          </button>
          <button className="investMake-next-button" onClick={handleNextStep}>
            {" "}
            다음 단계{" "}
          </button>
        </div>
        <div className="button-botoom-margin"></div>
      </div>
      <Footer />
    </>
  );
};
export default MakeInvest4;
