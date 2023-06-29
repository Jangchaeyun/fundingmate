import Header from "../../Component/Header/Header";
import {Route, Routes} from "react-router-dom";
import Footer from "../../Component/Footer/Footer";
import Main from "../../Component/Main/Main";
import Banner from "../../Component/Banner/Banner";

function Home() {
    return (
        <div className="home">
            <Header />
            <Banner />
            <Main />
            <Footer />
        </div>
    );
}
export default Home;