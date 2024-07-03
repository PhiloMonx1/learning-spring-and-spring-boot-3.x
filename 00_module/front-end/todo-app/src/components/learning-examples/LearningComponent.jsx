import FirstComponent from "./FirstComponent";
import SecondComponent from "./SecondComponent";
import ThirdComponent from "./ThirdComponent";
import FourthComponent from "./FourthComponent";
import {FifthComponent} from "./FirstComponent";
import LearningJavaScript from "./LearningJavaScript";

export default function LearningComponent() {
  return (
      <>
        <FirstComponent></FirstComponent>
        <SecondComponent></SecondComponent>
        <ThirdComponent></ThirdComponent>
        <FourthComponent></FourthComponent>
        <FifthComponent></FifthComponent>
        <LearningJavaScript></LearningJavaScript>
      </>
  );
}