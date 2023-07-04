import React, {useRef} from "react";
import { Route, useNavigate } from "react-router-dom";
import "./MakeReward3.css";
import "./MakeRewardCommon.css";
import {PlusOutlined, PlusSquareOutlined,MinusSquareOutlined} from "@ant-design/icons";
import { Button, Modal } from 'antd';
import { useState } from 'react';
import { DatePicker, Space } from 'antd';
const MakeReward3 = () => {
    const [modalOpen, setModalOpen] = useState(false);
    const initCard = {rewardAmount:'',rewardAvailableLimit:'',rewardAvailableCount:0,rewardTitle:'',rewardContent:'',rewardDeleverydate:'',rewardShipAddress:'',
        rewardOptName:'', rewardOptCon:''};
    const [card, setCard] = useState({...initCard});
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
    // const [rewardAmount, setRewardAmount] = useState("");
    // const [selectedDate, setSelectedDate] = useState(null);
    // const [rewardAvailableCount, setRewardAvailableCount] = useState("");
    // const handleRewAdd = () => {
    //     setEditingCardIndex(null);
    //     setRewardAmount("");
    //     setSelectedDate(null);
    //     setRewardAvailableCount("");
    //     setInputContent("");
    //     setTextareaContent("");
    //     setShowOption(false);
    //     setOptionFields([]);
    //     setLimitClicked(null);
    //
    //     setModalOpen(false);
    // };



    const handleOk = () => {
        setModalOpen(false);
        if(editingCardIndex!=null) {  //수정

        } else { //추가
            setCards(prevCards => [...prevCards, card]); // 새로운 카드 객체를 배열에 추가
        }
    };

    const handleCancel = () => {
        setModalOpen(false);


    };


    const [limitClicked, setLimitClicked] = useState(null);
    const handleLimitButtonClick = (isLimit) =>{
        setCard({...card, rewardAvailableLimit:isLimit })
        setLimitClicked(isLimit);
    }

    const [saClicked, setSaClicked] = useState(null);
    const handleSaButtonClick = (isAddress) =>{
        setCard({...card, rewardShipAddress:isAddress})
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
    const navigateToStep1 = useNavigate();
    const navigateToStep2 = useNavigate();

    const handlePreviousStep = () => {
        navigateToStep1("/make-reward/story");
    };

    const handleNextStep = () => {
        navigateToStep2("/make-reward/goodsinfo");
    };

    const rewardChange = (e) => {
        console.log(e);
        setCard({...card, [e.target.name]:e.target.value});
    }


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
                        setCard({...initCard});
                        //handleRewAdd();
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
                okText={editingCardIndex !== null ? '수정' : '추가'} //
                cancelText="취소"
                okButtonProps={{ style: { backgroundColor: "var(--main-color)" } }}

            >
                <p className="custom-font-modal-sub-title">
                    리워드 금액
                </p>
                <input type="text" name="rewardAmount" id="rew-amount-input" className="modal-input-box" placeholder="0"
                       value={card.rewardAmount}
                       onChange={rewardChange}
                />&nbsp;원

                <p className="custom-font-modal-sub-title">
                    리워드 제공 가능 수
                </p>
                <div style={{display:'flex', alignItems:'center'}}>
                <div className="rew-limit-buttons">
                    <button className={`rew-unlimited ${limitClicked === 'rew-unlimited-button' ? 'clicked' : ''}`}
                            id="rew-unlimited-button"
                            onClick={()=>handleLimitButtonClick(0)}
                    >무제한</button>
                    <button className={`rew-limit ${limitClicked==='rew-limit-button' ? 'clicked' : ''}`}
                            id="rew-limit-button"
                            onClick={()=>handleLimitButtonClick(1)}
                            >제한</button>

                </div>
                        {limitClicked === 1 && (
                            <div style={{display:'flex', alignItems:'center'}}>
                        <input type="text" name="rewardAvailableCount" className="modal-limit-input-box"  placeholder="0" value={card.rewardAvailableCount} onChange={rewardChange}/>&nbsp;개
                            </div>
                        )}

                </div>

                <p className="custom-font-modal-sub-title">
                    리워드 제목
                </p>
                <div>
                <input type="text" name="rewardTitle" className="modal-input-box" value={card.rewardTitle} onChange={rewardChange}/>
                <div style={{fontSize:"13px", color:"#939393"}}>{inputMaxLength-inputContent.length}자 남음</div>
                </div>
                <p className="custom-font-modal-sub-title">
                    리워드 내용
                </p>
                <div>
                <textarea type="text" name="rewardContent" className="rew-modal-textarea" value={card.rewardContent} onChange={rewardChange}/>
                <div style={{fontSize:"13px", color:"#939393"}}>{textareaMaxLength-textareaContent.length}자 남음</div>
                </div>


                <p className="custom-font-modal-sub-title">
                    예상 배송일
                </p>

                <DatePicker style={{width:"240px", height:"30px"}} id="rew-date-picker"
                            value={card.rewardDeleverydate} name="rewardDeleverydate"  onChange={(value, dateString) => { setCard({...card, rewardDeleverydate:dateString}); }}
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
                       {/* <p className="custom-font-modal-option-text" style={{color:"#BC8700"}}>
                                옵션{index+1}
                        </p>*/}
                      <div style={{display:'flex', alignItems:'center'}}>
                          <p style={{fontSize:"13px"}}>옵션명</p>
                          <p style={{marginLeft:"100px", fontSize:"13px"}}>옵션 값</p>
                      </div>
                        <div style={{display:'flex', alignItems:'center'}}>
                            <input type="text" name="rewardOptName" className="reward-opt-names" placeholder="예시)사이즈" value={card.rewardOptName} onChange={rewardChange}/>
                            <input type="text" name="rewardOptCon" className="reward-opt-con" placeholder="예시)S, M, L"  value={card.rewardOptCon} style={{marginLeft:"18px"}} onChange={rewardChange}/>
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
                            onClick={()=>handleSaButtonClick(1)}
                    >배송지 필요</button>
                    <button className={`rew-noSar ${saClicked==='rew-noSar-button' ? 'clicked' : ''}`}
                            id="rew-noSar-button"
                            onClick={()=>handleSaButtonClick(0)}
                    >배송지 필요없음</button>
                </div>
                </div>

            </Modal>
            {cards.map((cardItem, index) => (
            <div className="make-rew-card">
                <div className="make-rew-card-div">
                <div>
                <div className="make-rew-card-price">{cardItem.rewardAmount}원</div>
                <div className="make-rew-card-contents">
                    <div className="make-rew-card-count">{cardItem.rewardAvailableLimit==1? '제한':'무제한'}&nbsp;|&nbsp;0개 펀딩</div>
                    <div className="make-rew-card-date">예상 배송일 {cardItem.rewardDeleverydate}</div>
                </div>

                <div className="make-rew-card-sub-content">{cardItem.rewardTitle}</div>
                <div className="make-rew-card-sub-content-info">
                    {cardItem.rewardContent}
                </div>
                </div>
                <div>
                <div className="make-rew-card-edit-button-div">
                    <button className="make-rew-card-edit-button">복사</button>
                    <button  className="make-rew-card-edit-button" onClick={() => {
                        setCard({...cardItem})
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
            <button className="investMake-prev-button" onClick={handlePreviousStep}> 이전 단계 </button>
            <button className="investMake-next-button" onClick={handleNextStep}> 다음 단계 </button>
            </div>
                <div className="button-botoom-margin"></div>

        </div>
        </>
    );
};
export default MakeReward3;
