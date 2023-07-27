import Header from "../../Component/Header/Header";
import { Route, Routes } from "react-router-dom";
import Footer from "../../Component/Footer/Footer";
import Main from "../../Component/Main/Main";
import Banner from "../../Component/Banner/Banner";
import { useEffect, useState } from "react";
import axios from "axios";

function Home() {
  const [bannerData, setBannerData] = useState([]);
  const [rewardData, setRewardData] = useState([]);
  const [investData, setinvestData] = useState([]);
  useEffect(() => {
    // Banner 데이터 가져오기
    axios
      .get("http://localhost:8080/bannerList")
      .then((res) => {
        setBannerData([...res.data]);
        console.log("성공");
        console.log(res.data);
      })
      .catch((err) => {
        console.log("실패");
        console.log(err);
      });
    // Project 데이터 가져오기
    axios
      .get("http://localhost:8080/rewardList")
      .then((res) => {
        const resData = res.data;
        const addData = resData.map((item) => {
          const projDateEnd = new Date(item.projDateEnd);
          const today = new Date();
          const differenceInTime = Math.abs(
            projDateEnd.getTime() - today.getTime()
          );
          const differenceInDays = Math.ceil(
            differenceInTime / (1000 * 3600 * 24) - 1
          );
          if (item.projTargetAmount == null || item.projTargetAmount == "") {
            item.projTargetAmount = 0;
          }
          const formattedAmount = item.projTargetAmount.toLocaleString();
          if (item.paymentamountSum == null || item.paymentamountSum == "") {
            item.paymentamountSum = 0;
          }
          const paymentamountPercent = Math.ceil(
            item.paymentamountSum / item.projTargetAmount
          );
          const formattedPaymentamountSum = item.paymentamountSum;

          const formattedPaymentamountPercent = Math.ceil(
            (formattedPaymentamountSum / item.projTargetAmount) * 100
          );

          return {
            ...item,
            differenceInDays,
            formattedAmount,
            paymentamountPercent,
            formattedPaymentamountSum,
            formattedPaymentamountPercent
          };
        });
        setRewardData(addData);
      })
      .catch((err) => {
        console.log(err);
      });
    axios
      .get("http://localhost:8080/InvestList")
      .then((res) => {
        console.log("인베스트 확인");
        console.log("성공");
        const resData = res.data;
        console.log(res.data);
        const addData = resData.map((item) => {
          const investProjDateEnd = new Date(item.investProjDateEnd);
          const today = new Date();
          const differenceInTime = Math.abs(
            investProjDateEnd.getTime() - today.getTime()
          );
          const differenceInDays = Math.ceil(
            differenceInTime / (1000 * 3600 * 24) - 1
          );

          let paymentamountPercent;
          let formattedAmount;
          if (item.paymentamountSum == null || item.projTargetAmount == null) {
            formattedAmount = 0;
            paymentamountPercent = 0;
          } else {
            formattedAmount = item.projTargetAmount.toLocaleString();
            paymentamountPercent = Math.ceil(
              item.paymentamountSum / item.projTargetAmount
            );
          }
          console.log(item.paymentamountSum);
          console.log(item.projTargetAmount);
          console.log(paymentamountPercent);
          if (item.paymentamountSum == null || item.paymentamountSum == "") {
            item.paymentamountSum = 0;
          }
          let formattedPaymentamountSum =
            item.paymentamountSum.toLocaleString();
          if (formattedPaymentamountSum == NaN) {
            formattedPaymentamountSum = 0;
          }
          let formattedPaymentamountPercent =
            paymentamountPercent.toLocaleString();
          if (formattedPaymentamountPercent == NaN) {
            formattedPaymentamountPercent = 0;
          }
          return {
            ...item,
            differenceInDays,
            formattedAmount,
            formattedPaymentamountSum,
            formattedPaymentamountPercent
          };
        });
        setinvestData(addData);
      })
      .catch((err) => {
        console.log(err);
      });
    // axios
    //     .get("http://localhost:8080/continueList")
    //     .then((res) => {
    //         // console.log("성공")
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
    //     })
    //     .catch((err) => {
    //         console.log(err);
    //     });
  }, []);
  return (
    <div className="home">
      <Header />
      <Banner bannerData={bannerData} />
      <Main
        rewardData={rewardData}
        investData={investData} /*continueData={continueData}*/
      />
      <Footer />
    </div>
  );
}
export default Home;
