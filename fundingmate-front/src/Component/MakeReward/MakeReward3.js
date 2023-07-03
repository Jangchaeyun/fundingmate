import React, {useRef} from "react";
import "./MakeReward3.css";
import "./MakeRewardCommon.css";
import {PlusOutlined, PlusSquareOutlined,MinusSquareOutlined} from "@ant-design/icons";
import { Button, Modal } from 'antd';
import { useState } from 'react';
import { DatePicker, Space } from 'antd';
const MakeReward3 = () => {
    const [modalOpen, setModalOpen] = useState(false);
    const [cards, setCards] = useState([]); // 카드 배열 추가
    const [editingCardIndex, setEditingCardIndex] = useState(null); // 수정 중인 카드의 인덱스
/*    const initialState = {
        modalOpen: false,
        rewardAmount: "",
        rewardAvailableCount: "",
        inputContent: "",
        textareaContent: "",
        limitClicked: null,
        saClicked: null,
        showOption: false,
        optionFields: [],
    };*/
    const [rewardAmount, setRewardAmount] = useState("");
    const [selectedDate, setSelectedDate] = useState(null);
    const [rewardAvailableCount, setRewardAvailableCount] = useState("");
    const handleRewAdd = () => {
        setModalOpen(false);
        setInputContent("");
        setTextareaContent("");
        setShowOption(false);
        setOptionFields([]);
        setLimitClicked(null); // 리워드 제공 가능 수 초기화
        setSaClicked(null); // 배송지 필요 여부 초기화
        setRewardAmount("");
        setSelectedDate(null);

    };

    const handleOk = () => {
        if (editingCardIndex !== null) {
            // 수정 중인 카드의 정보 업데이트
            const updatedCards = [...cards];
            updatedCards[editingCardIndex] = {
                ...updatedCards[editingCardIndex],
                rewardAmount,
                rewardAvailableCount: limitClicked === 'rew-limit-button' ? rewardAvailableCount : '무제한',
                rewardTitle: inputContent,
                rewardContent: textareaContent,
                expectedDeliveryDate: selectedDate ? selectedDate.format('YYYY.MM.DD') : '',
                rewardOptions: optionFields.map((field) => field.rewardOptName),
                shippingAddressRequired: saClicked === 'rew-sar-button' ? '배송지 필요' : '배송지 필요없음',
            };
            setCards(updatedCards);
        } else {
            // 새로운 카드 추가
            setCards((prevCards) => [
                ...prevCards,
                {
                    rewardAmount,
                    rewardAvailableCount: limitClicked === 'rew-limit-button' ? rewardAvailableCount : '무제한',
                    rewardTitle: inputContent,
                    rewardContent: textareaContent,
                    expectedDeliveryDate: selectedDate ? selectedDate.format('YYYY.MM.DD') : '',
                    rewardOptions: optionFields.map((field) => field.rewardOptName),
                    shippingAddressRequired: saClicked === 'rew-sar-button' ? '배송지 필요' : '배송지 필요없음',
                },
            ]);
        }
        setModalOpen(false);
    };
    const handleCancel = () => {
        setModalOpen(false);


    };


    const [limitClicked, setLimitClicked] = useState(null);
    const handleLimitButtonClick = (buttonId) =>{
        setLimitClicked(buttonId);
    }

    const [saClicked, setSaClicked] = useState(null);
    const handleSaButtonClick = (buttonId) =>{
        setSaClicked(buttonId);
    }

    const [textareaContent, setTextareaContent] = useState("");
    const textareaMaxLength = 400;
    const handleMaxLengthChange = (event) => {
        const {value} = event.target;
        if(value.length <= textareaMaxLength){
            setTextareaContent(value);
        }
    };
    const [inputContent, setInputContent] = useState("");
    const inputMaxLength = 60;
    const handleInputChange = (event) => {
        const {value} = event.target;
        if(value.length <= inputMaxLength){
            setInputContent(value);
        }
    };

    const onChange = (date, dateString) => {
        console.log(date, dateString);
    };

    const [showOption, setShowOption] = useState(false);
    const [optionFields, setOptionFields] = useState([]);
    const handleShowOptionButtonClick =()=>{
        setShowOption(true);
        handleAddOption();
    };
    const handleAddOption = ()=>{
        setOptionFields([...optionFields,{}]);
    };

    const handleDeleteOption =(index)=>{
        const updatedOptions=[...optionFields];
        updatedOptions.splice(index,1);
        setOptionFields(updatedOptions);
        if (updatedOptions.length === 0) {
            setShowOption(false);
        }
    };

    return (
        <>
        <div className="investMake-wrapper">

            <div className="proj-progress-div">
                <div className="proj-progress proj-progress-common proj-progress-line">1</div>
                <div className="proj-progress proj-progress-common proj-progress-line">2</div>
                <div className="proj-progress-ing proj-progress-common proj-progress-line">3</div>
                <div className="proj-progress proj-progress-common proj-progress-line">4</div>
                <div className="proj-progress proj-progress-common">5</div>
            </div>


            <p className="custom-font-sub-title">
                <b>프로젝트 리워드를 구성해주세요</b>
            </p>

            <p className="custom-font-text">
                프로젝트 시작을 위해서는 <b style={{color:"#E93232"}}>최소 1개 이상의 리워드</b>가 있어야 합니다.<br/>
                배송이 필요한 리워드는 배송비가 포함된 가격을 적어주세요.
            </p>


          {/*  <button className="rew-card-add-button">
                <PlusOutlined className="rew-card-add-icon" style={{fontSize:"11px", marginRight:"1px"}}/>리워드 추가
            </button>*/}

            <Button type="primary"  className="rew-card-add-button" id="rew-card-add-button"
                    icon={<PlusOutlined id="rew-card-add-icon" style={{fontSize:"11px", marginRight:"1px"}}/>}
                    onClick={() => {
                        handleRewAdd();
                        setModalOpen(true);
                    }}>
                리워드 추가
            </Button>
            <Modal
                title={<p style={{ fontSize: "25px" }}>리워드 추가</p>}
                centered
                visible={modalOpen}
                onOk={handleOk}
                onCancel={handleCancel}
                width={655}
                okText="추가" // 'OK' 버튼의 이름 변경
                cancelText="취소"
                okButtonProps={{ style: { backgroundColor: "var(--main-color)" } }}

            >
                <p className="custom-font-modal-sub-title">
                    리워드 금액
                </p>
                <input type="text" name="rewardAmount" id="rew-amount-input" className="modal-input-box" placeholder="0"
                       value={rewardAmount}
                       onChange={(e) => setRewardAmount(e.target.value)}
                />&nbsp;원

                <p className="custom-font-modal-sub-title">
                    리워드 제공 가능 수
                </p>
                <div style={{display:'flex', alignItems:'center'}}>
                <div className="rew-limit-buttons">
                    <button className={`rew-unlimited ${limitClicked === 'rew-unlimited-button' ? 'clicked' : ''}`}
                            id="rew-unlimited-button"
                            onClick={()=>handleLimitButtonClick("rew-unlimited-button")}
                    >무제한</button>
                    <button className={`rew-limit ${limitClicked==='rew-limit-button' ? 'clicked' : ''}`}
                            id="rew-limit-button"
                            onClick={()=>handleLimitButtonClick("rew-limit-button")}
                            >제한</button>

                </div>
                        {limitClicked === 'rew-limit-button' && (
                            <div style={{display:'flex', alignItems:'center'}}>
                        <input type="text" name="rewardAvailableCount" className="modal-limit-input-box"  placeholder="0"/>&nbsp;개
                            </div>
                        )}

                </div>

                <p className="custom-font-modal-sub-title">
                    리워드 제목
                </p>
                <div>
                <input type="text" name="rewardTitle" className="modal-input-box" value={inputContent} onChange={handleInputChange}/>
                <div style={{fontSize:"13px", color:"#939393"}}>{inputMaxLength-inputContent.length}자 남음</div>
                </div>
                <p className="custom-font-modal-sub-title">
                    리워드 내용
                </p>
                <div>
                <textarea type="text" name="rewardContent" className="rew-modal-textarea" value={textareaContent} onChange={handleMaxLengthChange}/>
                <div style={{fontSize:"13px", color:"#939393"}}>{textareaMaxLength-textareaContent.length}자 남음</div>
                </div>


                <p className="custom-font-modal-sub-title">
                    예상 배송일
                </p>

                <DatePicker style={{width:"240px", height:"30px"}} id="rew-date-picker" onChange={onChange}
                            value={selectedDate}
                            onChange={(date) => setSelectedDate(date)}
                />

                <p className="custom-font-modal-sub-title">
                    리워드 옵션
                </p>

                {!showOption &&(
                <button className="rew-option-show-button" onClick={handleShowOptionButtonClick}>
                    리워드 옵션 추가하기
                </button>
                )}
                {showOption && (
                    <>
                    {optionFields.map((field, index)=>(

                  <div key={index} >
                        <p className="custom-font-modal-option-text">
                                옵션{index+1}
                        </p>
                        <div style={{display:'flex', alignItems:'center'}}>
                            <input type="text" name="rewardOptName" className="reward-opt-names" placeholder="예시)옷 사이즈를 적어주세요 (S, M, L)"/>
                            <button className="rew-add" onClick={handleAddOption}><PlusSquareOutlined style={{ fontSize: "23px"}} /></button>
                            <button className="rew-delete" onClick={()=>handleDeleteOption(index)}><MinusSquareOutlined style={{ fontSize: "23px"}} /></button>
                        </div>
                  </div>
                        ))}
                    </>
                )}

                <p className="custom-font-modal-sub-title">
                    배송지 필요여부
                </p>
                <div style={{display:'flex', alignItems:'center'}}>
                <div className="rew-sa-buttons">
                    <button className={`rew-sar ${saClicked === 'rew-sar-button' ? 'clicked' : ''}`}
                            id="rew-sar-button"
                            onClick={()=>handleSaButtonClick("rew-sar-button")}
                    >배송지 필요</button>
                    <button className={`rew-noSar ${saClicked==='rew-noSar-button' ? 'clicked' : ''}`}
                            id="rew-noSar-button"
                            onClick={()=>handleSaButtonClick("rew-noSar-button")}
                    >배송지 필요없음</button>
                </div>
                </div>

            </Modal>
            {cards.map((card, index) => (
            <div className="make-rew-card">
                <div className="make-rew-card-div">
                <div>
                <div className="make-rew-card-price">12,345원</div>
                <div className="make-rew-card-contents">
                    <div className="make-rew-card-count">무제한&nbsp;|&nbsp;0개 펀딩</div>
                    <div className="make-rew-card-date">예상 배송일 2023.07.23</div>
                </div>

                <div className="make-rew-card-sub-content">실버 커팅볼 스퀘어 체인 여자 팔찌</div>
                <div className="make-rew-card-sub-content-info">
                    실버 커팅볼 스퀘어 체인 여자 팔찌 1개 분량입니다.
                </div>
                </div>
                <div>
                <div className="make-rew-card-edit-button-div">
                    <button className="make-rew-card-edit-button">복사</button>
                    <button  className="make-rew-card-edit-button" onClick={() => {
                        setEditingCardIndex(index); // 수정 중인 카드의 인덱스 저장
                        setModalOpen(true); // 모달 열기
                    }}>수정</button>
                    <button  className="make-rew-card-edit-button make-rew-card-edit-button-delete">삭제</button>
                </div>
                </div>
                </div>
            </div>
            ))}

            <div className="button-top-margin"></div>
            <div className="investMake-button-div">
            <button className="investMake-prev-button"> 이전 단계 </button>
            <button className="investMake-next-button"> 다음 단계 </button>
            </div>
                <div className="button-botoom-margin"></div>

        </div>
        </>
    );
};
export default MakeReward3;
