/* eslint-disable react/prop-types */

import { useState } from "react";

const EnrolmentForm = ({ program }) => {
  const [firstName, setFirstName] = useState("");
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    setMessage("Thank you " + program + " for enrolling!");
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label htmlFor="firstName">First Name:</label>
        <input
          type="text"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <br />
        <label htmlFor="email">Email:</label>
        <input
          type="text"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <br />
        <input type="submit" value="Submit" />
        <br />
        <p>{message}</p>
      </form>
    </div>
  );
};

export default EnrolmentForm;



