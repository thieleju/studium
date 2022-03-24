import express from "express";
const router = express.Router();

import login from "./auth/login";
import token from "./auth/token";

router.use("/login", login);
router.use("/token", token);

export default router;
