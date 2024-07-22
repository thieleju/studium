import { useState } from "react";
import EnrolmentForm from "./EnrolmentForm";

function App() {
  const [program, setProgram] = useState("");

  return (
    <>
      <select type="text" onChange={(e) => setProgram(e.target.value)}>
        <option value="UG">UG</option>
        <option value="PG">PG</option>
      </select>
      <EnrolmentForm program={program} />
    </>
  );
}

export default App;

