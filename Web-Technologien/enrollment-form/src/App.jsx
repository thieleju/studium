import { useState } from "react";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <h1>count is {count}</h1>
      <button onClick={() => setCount(count + 1)}>Click to Add one</button>
    </>
  );
}

export default App;
