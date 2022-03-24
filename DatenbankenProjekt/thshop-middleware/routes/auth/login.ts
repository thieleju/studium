import express from "express";
const router = express.Router();

const clientId: string = process.env.CLIENT_ID;

router.get("/", (req, res) => {
  res.redirect(
    "https://github.com/login/oauth/authorize?client_id=" + clientId
  );
});

export default router;
