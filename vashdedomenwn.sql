--
-- PostgreSQL database cluster dump
--

-- Started on 2019-06-27 11:53:54

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md5438b79aa7bb14ba7cabba2594f60c514';






--
-- Database creation
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


\connect postgres

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 10.9
-- Dumped by pg_dump version 10.9

-- Started on 2019-06-27 11:53:54

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
-- TOC entry 2827 (class 0 OID 0)
-- Dependencies: 2826
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 2 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2829 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2830 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16396)
-- Name: admins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admins (
    id integer,
    username character varying(50),
    password character varying(50)
);


ALTER TABLE public.admins OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16402)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id integer,
    username character varying(50),
    password character varying(50)
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16408)
-- Name: clientsres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientsres (
    username character varying(50),
    movie character varying(100),
    "time" character varying(50),
    hall character varying(50)
);


ALTER TABLE public.clientsres OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16414)
-- Name: contentadmins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contentadmins (
    id integer,
    username character varying(50),
    password character varying(50)
);


ALTER TABLE public.contentadmins OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16411)
-- Name: halls; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.halls (
    id integer,
    nameofhall character varying(50)
);


ALTER TABLE public.halls OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16399)
-- Name: hours; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hours (
    id integer,
    "time" character varying(50)
);


ALTER TABLE public.hours OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16405)
-- Name: movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movies (
    id integer,
    nameofmovie character varying(100),
    "time" character varying(50),
    hall character varying(50)
);


ALTER TABLE public.movies OWNER TO postgres;

--
-- TOC entry 2814 (class 0 OID 16396)
-- Dependencies: 197
-- Data for Name: admins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admins (id, username, password) FROM stdin;
1	Marios	036D063106B9B5B455F1B47C55B00046
2	Xan8os	A8AD0EC51D0D764199DCF846CA5BAF58
\.


--
-- TOC entry 2816 (class 0 OID 16402)
-- Dependencies: 199
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (id, username, password) FROM stdin;
1	Matilde	1BD3955D759AEE0408E8C61D7054BBF7
2	Zorina	7EDDDFC6B2E9B7A17211E4005591B31E
3	Killy	8FCB7752CBCEC4B40CAD80B60FCDBF56
4	See	669E5FEDA473DC5C2D627AC2F17B1FD3
5	Sylvester	875F5A5C6E9FD707325DC6085E33F5AF
6	Kingsley	077F18E8B72A37DB5F5B7208C14DF8A1
7	Kaila	420E45A828F1B3761B00A9D468D12243
8	Franky	2EC259DE69DA548390CE7F9DDC93BE40
9	Kikelia	649021D23E31C928C160ECEF2A52BE09
10	Luella	8C554B8A474E65FCF76AEC658CC7D3D0
\.


--
-- TOC entry 2818 (class 0 OID 16408)
-- Dependencies: 201
-- Data for Name: clientsres; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clientsres (username, movie, "time", hall) FROM stdin;
Matilde	Road Home, The (Wo de fu qin mu qin)	12:31	B5
Matilde	Hexed	1:38	B1
Matilde	Life of a King	21:59	B2
Matilde	Sphinx	14:47	B1
\.


--
-- TOC entry 2820 (class 0 OID 16414)
-- Dependencies: 203
-- Data for Name: contentadmins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contentadmins (id, username, password) FROM stdin;
1	Verla	30D1015D70C72DCE5796C6689B12E897
2	Rosalie	1259882D45958328414C987DE2148CE4
3	Emera	54710CE547E80A7209462A19BE5F9157
4	Joni	EBB65C1C68C7A7A50F323D7631376F92
5	Mack	11A3ACEF2A1AF2ABB63F5DDC14C30891
6	Kostas	2A0D515FB11F6EF9C1C7D01449ED1461
\.


--
-- TOC entry 2819 (class 0 OID 16411)
-- Dependencies: 202
-- Data for Name: halls; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.halls (id, nameofhall) FROM stdin;
1	A1
2	A2
3	A3
4	A4
5	A5
6	B1
7	B2
8	B3
9	B4
10	B5
\.


--
-- TOC entry 2815 (class 0 OID 16399)
-- Dependencies: 198
-- Data for Name: hours; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hours (id, "time") FROM stdin;
1	14:45
2	12:00
3	21:00
4	22:00
5	14:00
6	12:30
7	12:45
8	18:30
9	17:30
10	21:45
\.


--
-- TOC entry 2817 (class 0 OID 16405)
-- Dependencies: 200
-- Data for Name: movies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movies (id, nameofmovie, "time", hall) FROM stdin;
1	Panama Hattie	9:29	A1
2	At the Earth's Core	15:12	A2
4	Here Comes the Devil (Ahí va el diablo)	20:17	B3
5	Road Home, The (Wo de fu qin mu qin)	12:31	B5
6	Critical Care	16:14	A5
7	Riding Alone for Thousands of Miles (Qian li zou dan qi)	23:33	A3
8	Life of a King	21:59	B2
9	Xuxa in Crystal Moon	11:43	A3
10	Hexed	1:38	B1
11	Farmer's Wife, The	22:16	A1
12	Short Eyes	10:58	A4
13	Amar Akbar Anthony	7:36	B4
14	Days of Heaven	2:38	B2
15	Sphinx	14:47	B1
16	Strange Bedfellows	21:42	A4
17	Gabrielle	4:49	B3
18	East Meets West (Dung sing sai tsau 2011)	1:29	A5
19	Return to House on Haunted Hill	15:25	B5
20	Bathing Beauty	4:13	A2
21	avengers	14:45	A1
22	train	14:00	A5
3	Midnight Madness	12:00	A2
\.


-- Completed on 2019-06-27 11:53:56

--
-- PostgreSQL database dump complete
--

\connect template1

SET default_transaction_read_only = off;

--
-- PostgreSQL database dump
--

-- Dumped from database version 10.9
-- Dumped by pg_dump version 10.9

-- Started on 2019-06-27 11:53:56

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
-- TOC entry 2791 (class 0 OID 0)
-- Dependencies: 2790
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2793 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


-- Completed on 2019-06-27 11:54:00

--
-- PostgreSQL database dump complete
--

-- Completed on 2019-06-27 11:54:00

--
-- PostgreSQL database cluster dump complete
--

