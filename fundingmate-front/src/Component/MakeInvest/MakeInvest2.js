import React, {useState, useEffect} from "react";
import { Route, useNavigate, useLocation } from "react-router-dom";
import "./MakeInvest2.css";
import "./MakeInvestCommon.css";
import { DatePicker } from 'antd';
import 'dayjs/locale/zh-cn';
import dayjs from 'dayjs';
const { RangePicker } = DatePicker;

const MakeInvest2 = () => {
    const location = useLocation();
    const preTotInfo = location.state.totInfo;
    const [totInfo, setTotInfo] = useState(preTotInfo);

    const handleInputChange = (e) => {
        setTotInfo({...totInfo, [e.target.name]:e.target.value}) ;
    };

    const regexPattern = /^[0-9]*$/; // 숫자만 입력되도록 정규식 패턴 설정
    const handleNumInputChange = (e) => {
    const { name, value } = e.target;

     // 숫자만 입력되도록 검증
     if (regexPattern.test(value)) {
    setTotInfo({ ...totInfo, [name]: value });
        }
    };

    const navigateToStep1 = useNavigate();
    const navigateToStep2 = useNavigate();

    const handlePreviousStep = () => {
        navigateToStep1("/make-invest/basicinfo", {state:{totInfo:totInfo}});
    };

    const handleNextStep = () => {

        navigateToStep2("/make-invest/story", {state:{totInfo:totInfo}});
    };


    return (
        <>
        <div className="investMake-wrapper">
            <div className="proj-progress-div">
                <div className="proj-progress proj-progress-common proj-progress-line">1</div>
                <div className="proj-progress-ing proj-progress-common proj-progress-line">2</div>
                <div className="proj-progress proj-progress-common proj-progress-line">3</div>
                <div className="proj-progress proj-progress-common proj-progress-line">4</div>
                <div className="proj-progress proj-progress-common">5</div>
            </div>

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
export default MakeInvest2;
