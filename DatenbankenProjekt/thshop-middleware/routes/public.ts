import express from "express";
const router = express.Router();

import apps from "./public/apps";

router.use("/apps", apps);

export default router;
