import React, {useEffect, useState} from 'react';
import "../Content/Project.css"
import Project from "../Content/RewardProject";
import RewardProject from "../Content/RewardProject";
import InvestProject from "../Content/InvestProject";
import ContinueProject from "../Content/ContinueProject";

function Main(props) {
    const [rewardData, setrewardData] = useState([]);
    const [investData, setinvestData] = useState([]);
    const [continueData, setcontinueData] = useState([]);
    // const [reward, setReward] = useState([]);
    // const [visibleRewards, setVisibleRewards] = useState(4);

    useEffect(() => {
        setrewardData(props.rewardData); // 전달받은 데이터를 state로 설정
        setinvestData(props.investData); // 전달받은 데이터를 state로 설정
    }, [props.rewardData,props.investData]);
    return (
        <div className="container">
            <RewardProject rewardData={rewardData} /*title="리워드 프로젝트"*//>
            <InvestProject investData={investData}/*title="창업 프로젝트"*//>
            {/*<ContinueProject continueData={continueData}title="사전공개"/>*/}
        </div>
    );
}

export default Main;