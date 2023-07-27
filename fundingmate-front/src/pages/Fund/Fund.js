import "../../App.css";
import "../../pages/Fund/Fund.css";
import Finish from "../../Component/Fund/Finish";
import Funding from "../../Component/Fund/Funding";
import Preopen from "../../Component/Fund/Preopen";
import Realtime from "../../Component/Fund/realtime";
import Category from "../../Component/Category/Category";
import Banner from "../../Component/Banner/Banner";
import Header from "../../Component/Header/Header";
import Footer from "../../Component/Footer/Footer";

const Fund = () => {
  return (
    <div className="App">
      <Header />
      <Category />
      <Funding />
      <Preopen />
      <Realtime />
      <Finish />
      <Footer />
    </div>
  );
};

export default Fund;
