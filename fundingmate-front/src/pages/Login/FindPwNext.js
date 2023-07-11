import React from 'react';
import Header from "../../Component/Header/Header";
import CorFooter from "../../Component/Footer/CorFooter";
import FindIdPwForm from "../../Component/Login/FindIdPw/FindIdPwForm";
import FindPwNextForm from "../../Component/Login/FindIdPw/FindPwNextForm";

function FindPwNext(props) {
    return (
        <div className="wrap">
            <Header />
            <FindPwNextForm />
            <CorFooter />
        </div>
    );
}

export default FindPwNext;