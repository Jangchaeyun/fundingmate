import React from "react";
import Desc from "../../Component/RewardDetail/Desc/Desc";
import Contact from "../../Component/RewardDetail/Detail/Contact/Contact";
import Guide from "../../Component/RewardDetail/Detail/Guide/Guide";
import Story from "../../Component/RewardDetail/Detail/Story/Story";
import { Route, Routes } from "react-router-dom";
const Rewarddetail = () => {
  return (
    <div className="App">
      <Routes>
        <Route exact path="/reward-detail/story" element={<Story />} />
        <Route exact path="/reward-detail/contact" element={<Contact />} />
        <Route exact path="/reward-detail/guide" element={<Guide />} />
      </Routes>
      <Story />
    </div>
  );
};

export default Rewarddetail;
