import express from "express";
const router = express.Router();

import user from "./users/user";
import apps from "./users/apps";

router.use("/user", user);
router.use("/apps", apps);

export default router;
