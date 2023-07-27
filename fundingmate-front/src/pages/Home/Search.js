import Header from "../../Component/Header/Header";
import {Route, Routes, useLocation, useParams} from "react-router-dom";
import Footer from "../../Component/Footer/Footer";
import Main from "../../Component/Main/Main";
import Banner from "../../Component/Banner/Banner";
import SearchForm from "../../Component/Search/SearchForm";
import {useEffect, useState } from "react";
import axios from "axios";

function Search(props) {
    const [bannerData, setBannerData] = useState([]);
    const [rewardData, setRewardData] = useState([]);
    const [investData, setInvestData] = useState([]);
    const [count, setCount] = useState(0);
    const { state } = useLocation();
    // const [keyword, setKeyword] = useState(state.word ? state.word : "");

    const {keyword} = useParams();
    useEffect(() => {
        // Project 데이터와 Invest 데이터를 병렬로 가져오기
        Promise.all([
            axios.get(`http://localhost:8080/rewardList?word=${keyword}`),
            axios.get(`http://localhost:8080/InvestList?word=${keyword}`)
        ])
            .then((responses) => {
                const [rewardRes, investRes] = responses;
                const rewardData = rewardRes.data.map((item) => {
                    // rewardData 처리 로직
                    const projDateEnd = new Date(item.projDateEnd);
                    const today = new Date();
                    const differenceInTime = Math.abs(projDateEnd.getTime() - today.getTime());
                    const differenceInDays = Math.ceil(differenceInTime / (1000 * 3600 * 24) - 1);
                    if(item.projTargetAmount == null || item.projTargetAmount == ''){
                        item.projTargetAmount = 0;
                    }
                    const formattedAmount = item.projTargetAmount.toLocaleString();
                    if(item.paymentamountSum == null || item.paymentamountSum == ''){
                        item.paymentamountSum = 0;
                    }
                    const paymentamountPercent = Math.ceil(item.paymentamountSum / item.projTargetAmount);
                    const formattedPaymentamountSum = item.paymentamountSum.toLocaleString();
                    const formattedPaymentamountPercent = paymentamountPercent.toLocaleString();

                    return { ...item, differenceInDays, formattedAmount , formattedPaymentamountSum, formattedPaymentamountPercent };
                });
                const investData = investRes.data.map((item) => {
                    // investData 처리 로직
                    const investProjDateEnd = new Date(item.investProjDateEnd);
                    const today = new Date();
                    const differenceInTime = Math.abs(investProjDateEnd.getTime() - today.getTime());
                    const differenceInDays = Math.ceil(differenceInTime / (1000 * 3600 * 24) - 1);


                    let paymentamountPercent;
                    let formattedAmount;
                    if(item.paymentamountSum == null || item.projTargetAmount == null){
                        formattedAmount = 0;
                        paymentamountPercent = 0;
                    }else {
                        formattedAmount = item.projTargetAmount.toLocaleString();
                        paymentamountPercent = Math.ceil(item.paymentamountSum / item.projTargetAmount);
                    }
                    console.log(item.paymentamountSum);
                    console.log(item.projTargetAmount);
                    console.log(paymentamountPercent);
                    if(item.paymentamountSum == null || item.paymentamountSum == '') {
                        item.paymentamountSum = 0;
                    }
                    let formattedPaymentamountSum = item.paymentamountSum.toLocaleString();
                    if(formattedPaymentamountSum == NaN) {
                        formattedPaymentamountSum = 0;
                    }
                    let formattedPaymentamountPercent = paymentamountPercent.toLocaleString();
                    if(formattedPaymentamountPercent == NaN) {
                        formattedPaymentamountPercent = 0;
                    }
                    return { ...item, differenceInDays, formattedAmount , formattedPaymentamountSum, formattedPaymentamountPercent };
                });
                setRewardData(rewardData);
                setInvestData(investData);

                // 처리 완료 후 cnt 값 확인 및 계산
                // const totalCount = rewardData.length + investData.length;
                // alert(rewardData.length)
                // alert(investData.length)
                // setCount(totalCount);
            })
            .catch((err) => {
                console.log(err);
            });
        // Project 데이터 가져오기
        // axios
        //     .get(`http://localhost:8080/rewardList?word=${keyword}`)
        //     .then((res) => {
        //         const resData = res.data;
        //         const addData = resData.map((item) => {
        //             const projDateEnd = new Date(item.projDateEnd);
        //             const today = new Date();
        //             const differenceInTime = Math.abs(projDateEnd.getTime() - today.getTime());
        //             const differenceInDays = Math.ceil(differenceInTime / (1000 * 3600 * 24) - 1);
        //             if(item.projTargetAmount == null || item.projTargetAmount == ''){
        //                 item.projTargetAmount = 0;
        //             }
        //             const formattedAmount = item.projTargetAmount.toLocaleString();
        //             if(item.paymentamountSum == null || item.paymentamountSum == ''){
        //                 item.paymentamountSum = 0;
        //             }
        //             const paymentamountPercent = Math.ceil(item.paymentamountSum / item.projTargetAmount);
        //             const formattedPaymentamountSum = item.paymentamountSum.toLocaleString();
        //             const formattedPaymentamountPercent = paymentamountPercent.toLocaleString();
        //             return { ...item, differenceInDays, formattedAmount , formattedPaymentamountSum, formattedPaymentamountPercent };
        //         });
        //         setRewardData(addData);
        //         // window.location.reload();
        //         setCount(count + addData.length);
        //     })
        //     .catch((err) => {
        //         console.log(err);
        //     });
        // axios
        //     .get(`http://localhost:8080/InvestList?word=${keyword}`)
        //     .then((res) => {
        //         console.log("성공")
        //         const resData = res.data;
        //         console.log(res.data)
        //         const addData = resData.map((item) => {
        //             const investProjDateEnd = new Date(item.investProjDateEnd);
        //             const today = new Date();
        //             const differenceInTime = Math.abs(investProjDateEnd.getTime() - today.getTime());
        //             const differenceInDays = Math.ceil(differenceInTime / (1000 * 3600 * 24) - 1);
        //
        //
        //             let paymentamountPercent;
        //             let formattedAmount;
        //             if(item.paymentamountSum == null || item.projTargetAmount == null){
        //                 formattedAmount = 0;
        //                 paymentamountPercent = 0;
        //             }else {
        //                 formattedAmount = item.projTargetAmount.toLocaleString();
        //                 paymentamountPercent = Math.ceil(item.paymentamountSum / item.projTargetAmount);
        //             }
        //             console.log(item.paymentamountSum);
        //             console.log(item.projTargetAmount);
        //             console.log(paymentamountPercent);
        //             if(item.paymentamountSum == null || item.paymentamountSum == '') {
        //                 item.paymentamountSum = 0;
        //             }
        //             let formattedPaymentamountSum = item.paymentamountSum.toLocaleString();
        //             if(formattedPaymentamountSum == NaN) {
        //                 formattedPaymentamountSum = 0;
        //             }
        //             let formattedPaymentamountPercent = paymentamountPercent.toLocaleString();
        //             if(formattedPaymentamountPercent == NaN) {
        //                 formattedPaymentamountPercent = 0;
        //             }
        //             return { ...item, differenceInDays, formattedAmount , formattedPaymentamountSum, formattedPaymentamountPercent };
        //         });
        //         setInvestData(addData);
        //         // setCount(addData.length);
        //         // setCount(count + +addData.length);
        //         setCount(count + addData.length);
        //     })
        //     .catch((err) => {
        //         console.log(err);
        //     });
    },[keyword])
    return (
        <div className="home">
            <Header/>
            <SearchForm word={keyword} count={count}/>
            <Main rewardData={rewardData} investData={investData} /*continueData={continueData}*//>
            <Footer />
        </div>
    );
}
export default Search;