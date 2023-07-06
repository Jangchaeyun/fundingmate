import Header from "../../Component/Header/Header";
import {Route, Routes} from "react-router-dom";
import Footer from "../../Component/Footer/Footer";
import Main from "../../Component/Main/Main";
import Banner from "../../Component/Banner/Banner";
import SearchForm from "../../Component/Search/SearchForm";

function Home() {
    return (
        <div className="home">
            <Header />
            <SearchForm />
            <Main />
            <Footer />
        </div>
    );
}
export default Home;