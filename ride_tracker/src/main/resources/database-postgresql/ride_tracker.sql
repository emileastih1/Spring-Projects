--
-- PostgreSQL database dump
--

-- Dumped from database version 10.9
-- Dumped by pg_dump version 10.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ride; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ride (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    duration integer NOT NULL,
    ride_date timestamp without time zone
);


ALTER TABLE public.ride OWNER TO postgres;

--
-- Name: ride_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ride_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ride_id_seq OWNER TO postgres;

--
-- Name: ride_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ride_id_seq OWNED BY public.ride.id;


--
-- Name: ride id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride ALTER COLUMN id SET DEFAULT nextval('public.ride_id_seq'::regclass);


--
-- Data for Name: ride; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ride (id, name, duration, ride_date) FROM stdin;
2	Willow trail Ride	30	2020-04-13 13:53:34.386
3	Round valey Ride	38	2020-04-13 13:53:34.386
4	Emile Ride	38	2020-04-13 13:53:34.386
1	Bobsled trail Ride	37	2020-04-13 13:53:34.386
7	Yellow fork Ride	38	2020-04-13 13:53:34.386
8	Yellow fork Ride	38	2020-04-13 13:53:34.386
\.


--
-- Name: ride_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ride_id_seq', 8, true);


--
-- Name: ride ride_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride
    ADD CONSTRAINT ride_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

