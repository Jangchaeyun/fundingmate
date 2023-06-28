import Header from "../../Component/Header/Header";
import {Route, Routes} from "react-router-dom";
import Footer from "../../Component/Footer/Footer";

function Home() {
    return (
        <div className="home">
            <Header />
            <Routes>
                <Route exact path="/" element={<Home />} />
                {/*<Route exact path="/reward" element={<Reward />} />*/}
            </Routes>
            <Footer />
        </div>
    );
}
export default Home;