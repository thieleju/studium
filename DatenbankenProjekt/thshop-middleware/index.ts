import express from "express";
import dotenv from "dotenv";
import cors from "cors";
import helmet from "helmet";

const app = express();

// set dynamic environment variables
// NODE_ENV is set in the npm scripts
dotenv.config({ path: "./.env." + process.env.NODE_ENV });

const port: number = Number(process.env.SERVER_PORT);
const origin: string = process.env.CORS_ORIGIN;

// secure express app
app.use(helmet());

// protect with cors
app.use(
  cors({
    methods: ["GET", "POST"],
    origin,
  })
);

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// check if provided data is a valid json to catch unhandled errors
app.use((err: any, req: any, res: any, next: any) => {
  if (err)
    res.status(400).json({
      status: "error",
      message: "Invalid request!",
      error: err.toString(),
    });
});

app.get("/", (req: any, res: any) => {
  res.status(200).json({
    success: true,
    message: "API Online!",
    authorization: req.headers.authorization,
  });
});

// import routes
import authRouter from "./routes/auth";
import publicRouter from "./routes/public";
app.use("/auth", authRouter);
app.use("/public", publicRouter);

// Create server object
const server = app.listen(port, () => {
  console.log("Running in " + process.env.NODE_ENV + " mode on port " + port);

});
