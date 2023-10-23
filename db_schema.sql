--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.9 (Ubuntu 14.9-0ubuntu0.22.04.1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    salt character varying(16) NOT NULL,
    privilege character varying(5),
    is_blocked boolean DEFAULT false NOT NULL,
    created_at timestamp with time zone NOT NULL
);


ALTER TABLE public.author OWNER TO postgres;

--
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.author ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: hidden_message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hidden_message (
    id bigint NOT NULL,
    message_uuid uuid NOT NULL,
    created_at timestamp with time zone NOT NULL,
    hidden_by bigint NOT NULL,
    reason text
);


ALTER TABLE public.hidden_message OWNER TO postgres;

--
-- Name: hidden_message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.hidden_message ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hidden_message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    uuid uuid NOT NULL,
    author_id bigint NOT NULL,
    thread_id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    parent_uuid uuid,
    body text,
    image bytea,
    is_active boolean NOT NULL
);


ALTER TABLE public.message OWNER TO postgres;

--
-- Name: reaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reaction (
    id bigint NOT NULL,
    author_id bigint NOT NULL,
    message_uuid uuid NOT NULL,
    created_at timestamp with time zone NOT NULL,
    type character varying(5) NOT NULL
);


ALTER TABLE public.reaction OWNER TO postgres;

--
-- Name: reaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reaction ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: reported_message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reported_message (
    id bigint NOT NULL,
    message_uuid uuid NOT NULL,
    flagged_by bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    reason text
);


ALTER TABLE public.reported_message OWNER TO postgres;

--
-- Name: reported_message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reported_message ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reported_message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.room (
    id bigint NOT NULL,
    uuid uuid NOT NULL,
    title character varying(100) NOT NULL,
    author_id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    is_public boolean NOT NULL,
    is_active boolean NOT NULL,
    exited_at timestamp with time zone
);


ALTER TABLE public.room OWNER TO postgres;

--
-- Name: room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription (
    id bigint NOT NULL,
    author_id bigint NOT NULL,
    room_id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    is_active boolean NOT NULL,
    exited_at timestamp with time zone,
    privilege character varying(5) NOT NULL
);


ALTER TABLE public.subscription OWNER TO postgres;

--
-- Name: subscription_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.subscription ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.subscription_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: thread; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.thread (
    id bigint NOT NULL,
    uuid uuid NOT NULL,
    title character varying(100) NOT NULL,
    author_id bigint NOT NULL,
    room_id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    is_active boolean NOT NULL,
    exited_at timestamp with time zone
);


ALTER TABLE public.thread OWNER TO postgres;

--
-- Name: thread_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.thread ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.thread_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.author (id, name, password, salt, privilege, is_blocked, created_at) FROM stdin;
1	author	password	salt	0	f	2023-10-17 15:57:31.875757+02
2	asd	abc	salt	user	f	2023-10-17 15:57:31.875757+02
3	username	{noop}password	salt	user	f	2023-10-17 15:57:31.875757+02
4	user	{bcrypt}$2a$10$0SHvlgMGoM51arnm919sse5mYtZnbmvjuoWs71Wzi7jWUea08BwQ2	salt	user	f	2023-10-17 15:57:31.875757+02
5	auth_user	{bcrypt}$2a$10$D.7jt1656kZ1PCYXe8o2yOb2k4ErprRQfJS19gbIgLpN1t5fJsoU2	salt	USER	f	2023-10-17 15:57:31.875757+02
6	new_user	{bcrypt}$2a$10$FnPC9DqI5w/.uSJ3XVoaeu2NPqxAB5iXjrKNsfiiayHmyjuchQKoq	salt	user	f	2023-10-17 15:57:31.875757+02
7	admin	{bcrypt}$2a$10$M6FM6WX3UESSs/pJao9i2ee6YLSO/U9zINNoeoP3b2IBomt0FmGSi	salt	admin	f	2023-10-17 15:57:31.875757+02
8	user1	{bcrypt}$2a$10$Chlyf.jD2kYt2rOolgThJumjYld1qsFNPB28S7XCtZduL1/3VNRsi	salt	ADMIN	f	2023-10-17 15:57:31.875757+02
10	new user	{bcrypt}$2a$10$B3/2gIsrnqDPphVEmoaw1.HoBZvOh9a2t93yJje3Z.9QZ3JR74pMi	salt	USER	f	2023-10-17 15:57:31.875757+02
12	user2	{bcrypt}$2a$10$hbdWSr0N3eio1ld4fCuEJu5iEbcYPiyqJQLStNj7PZnMRoJcdPll2	salt	USER	f	2023-10-17 15:57:31.875757+02
13	user3	{bcrypt}$2a$10$btxeMetSbQzuSZRdcF1SCutSD9bbotZYah1laMfqP70yvJddDpTb2	salt	USER	f	2023-10-17 15:57:31.875757+02
14	user4	{bcrypt}$2a$10$J.bEKfOL3/rm1XKP2WxTFe1BhrpWUPCN1UET4tJals2UGTdqIzbvK	salt	USER	f	2023-10-17 15:57:31.875757+02
15	user5	{bcrypt}$2a$10$eYI0w3H.ScLAwIY5EP9ClOLm87TgVZqPdF3fC3IQ34i.RS55iw8KO	salt	USER	f	2023-10-17 15:57:31.875757+02
16	user7	{bcrypt}$2a$10$5lLXrq/LjJggqFzfpqCUEugbKtD5sywM.RMcNd5MVr52U/8n6SJfG	salt	USER	f	2023-10-17 15:57:31.875757+02
17	user8	{bcrypt}$2a$10$PTGFeWG8N9.n02IZxFrT8OYwsv0C2be87GPc0yE7uSfN66YhH2kbu	salt	USER	f	2023-10-17 15:57:31.875757+02
18	user9	{bcrypt}$2a$10$ItmKefLujVRldndHJ8leO.tcfFJoMWYdQBOLviH7ehAfBUX8Zqiti	salt	USER	f	2023-10-17 15:57:31.875757+02
19	user10	{bcrypt}$2a$10$FoKVPM0192H0.187LFegYO.ZDqqdJhCOi8Mx5ChXcluEYlK9kpdD2	salt	USER	f	2023-10-17 15:57:31.875757+02
20	user11	{bcrypt}$2a$10$sdZC15615RG0A9la34XA8ukg0yewk2YkJvmjAxo1jLAJilBx40h4i	salt	USER	f	2023-10-17 15:57:31.875757+02
21	user12	{bcrypt}$2a$10$20kQ6uLq8fs/8PPb49ZlRez0g5UOwUN990JVqLuQrp4hfYAzBiyOq	salt	USER	f	2023-10-17 15:57:31.875757+02
23	user13	{bcrypt}$2a$10$Ya9/H2DnzP5xGaw5xy.TQucXMOWnnMjZiq2lL6Xh/fZ/vqExIKD8O	salt	USER	f	2023-10-17 15:57:31.875757+02
24	user14	{bcrypt}$2a$10$M2mYmQ1yFQTZu/H0pOnL8.WkH1.IIyQ0HwixYuQNMncuvy3YHRnBW	salt	USER	f	2023-10-17 15:57:31.875757+02
25	user15	{bcrypt}$2a$10$5Rf/NqoEXa1cmpSAEiytdulFzK5Ob36pOKZ.fEHhTHvdGdIVfAN8q	salt	USER	f	2023-10-17 15:57:31.875757+02
26	user16	{bcrypt}$2a$10$FaMm4HzUEe.Ei3kpKbmYwO8PfAUqN6acHal6GBsp8RjsuvcVoUMXm	salt	USER	f	2023-10-17 15:57:31.875757+02
28	user17	{bcrypt}$2a$10$Oi904Y3L6fXioHPFZBvWxeLbKBQG0k9ruSmqozcojqH9sFHgMlV8i	salt	USER	f	2023-10-17 15:57:31.875757+02
29	user18	{bcrypt}$2a$10$juu2APEmiEu6M8HAbIsS5eaeVVOjHubMy0c0ja3hhQ7rcnAdobAmy	salt	USER	f	2023-10-17 15:57:31.875757+02
30	user19	{bcrypt}$2a$10$r22X/ZEDBWJ5kAfDfiCMh.NwGmRyHNlOxPQf0K5ql35o2skdMQPPS	salt	USER	f	2023-10-17 15:57:31.875757+02
31	user21	{bcrypt}$2a$10$n6k4o/GJMIcFpzKzSJyZ6e8z3YGYZ53tT/X9cPY3BJ3t.PEcddPuG	salt	USER	f	2023-10-17 15:57:31.875757+02
32	user22	{bcrypt}$2a$10$x.csrCo1IYcCxS4lzRfKx.DnBSEL1NQImPQn1Mzl3qx0fKLZJOo8q	salt	USER	f	2023-10-17 15:57:31.875757+02
33	user23	{bcrypt}$2a$10$gYjXan8vvE6sT6ZNVybMFuwJqfGQu6qcOXlJ91r1xEB0Nvu6mZJqS	salt	USER	f	2023-10-17 15:57:31.875757+02
34	user24	{bcrypt}$2a$10$m8vM6jEMLgTloTDGmrD4nezsEvo7ahsQxozhp3FZbbGCSsA6tHVAG	salt	USER	f	2023-10-17 15:57:31.875757+02
35	user25	{bcrypt}$2a$10$t0k0WpOdTkRVqCoeCqu.AuG3P14YyxcfkW7eEl7CATz7X1Uo4jvNm	salt	USER	f	2023-10-17 15:57:31.875757+02
36	user26	{bcrypt}$2a$10$yqh2zQzyIWeVEuvOvRDL6.3.nXs19mrRwcywpf66ZCn3xBGuwFFg6	salt	USER	f	2023-10-17 15:57:31.875757+02
37	user27	{bcrypt}$2a$10$ojXnCESc8QbM4b33A6oeKOcfOT5MuxgL55cPNg9r1p.z2D9zK8Msa	salt	USER	f	2023-10-17 15:57:31.875757+02
38	user30	{bcrypt}$2a$10$3ap4Wi.ZrQbKpktKFvS2xe8DK0kjRwI6zA19izpKYeEQ6LV9SoCaC	salt	USER	f	2023-10-17 15:57:31.875757+02
40	user33	{bcrypt}$2a$10$RgD2NpD2gh9vgtRO34SASuTLlzGGrlr25jE9MRPtVJA/K5zKNspie	salt	USER	f	2023-10-17 16:12:13.737891+02
41	user34	{bcrypt}$2a$10$1pyeG4ouBvVhLS5is6SZkuqcHBBNIZrdAI/kfqfeYfrVkg8hWYu5y	salt	USER	f	2023-10-17 16:26:37.738283+02
42	user36	{bcrypt}$2a$10$6GmVlqyDeOKw3FmnRRJfueQnWAjI8yeSpVMDTFP3sECLkJpAn8hOO	salt	USER	f	2023-10-18 12:56:04.099168+02
43	user50	{bcrypt}$2a$10$t6Cos3ZqO9NF2Thjyl2aku2KnU9EyozWLF8rmLxBbZUX6uHZRK.q2	salt	USER	f	2023-10-20 11:05:18.472065+02
48	user55	{bcrypt}$2a$10$Uv4M/MuVHHHNuvyWKCBO4uobuUoxFEXJMkCHVLy1vGQ11E3wgcA9C	salt	USER	f	2023-10-20 16:55:05.680552+02
50	user56	{bcrypt}$2a$10$8yaS/5qFjFZJGX6owrcQweIz7xUi8iEo8AVHBS22A8KeZVGaY22s.	salt	USER	f	2023-10-20 17:12:11.236158+02
51	user57	{bcrypt}$2a$10$NUMW2akTka7/LZsxIrcyNutjo/NInk30W0eM8Jw6P5/tKA/J4Fzuy	salt	USER	f	2023-10-20 17:19:53.978587+02
52	user99	{bcrypt}$2a$10$mjaZCm9U9aBWnmIsra/w2ORZKR0K9hZPMTUKoyhBZ9vF9vsVL6KbS	salt	USER	f	2023-10-20 17:21:03.201619+02
53	user98	{bcrypt}$2a$10$pQcVbMk5bHxKSRq509dygus7LrzQyjya57u9vgGo.qoU0uPfZm7p2	salt	USER	f	2023-10-20 17:27:37.704542+02
54	user97	{bcrypt}$2a$10$T6stTj9N8klCzPK5OoC5qO42f/qS/CFF0fahhsZnLb8jDo3NKbXH2	salt	USER	f	2023-10-20 17:41:47.308684+02
55	user96	{bcrypt}$2a$10$0SA1OWjq3T/CKWx1HxBV7eQOE.bQBWSw.HSHFDbsogw4mZJY1Ak/u	salt	USER	f	2023-10-20 17:42:51.797772+02
56	user95	{bcrypt}$2a$10$i4eKMcegAp.yQcaBCb1VKevJe2zjR.LO0vxH.3.DShgsMDeneDb9m	salt	USER	f	2023-10-23 09:57:57.477828+02
57	user84	{bcrypt}$2a$10$cFa3/8oEavXfMTxTEa6cCuSNfOMOrRHk16DgTc8tyxMBx.IfDSG6i	salt	USER	f	2023-10-23 10:12:55.154591+02
58	user94	{bcrypt}$2a$10$xqwr023ROSOqN/usE79zh.0AbIJSxxXa8jM57kpNXoOBWgvCDcg8O	salt	USER	f	2023-10-23 10:13:48.343704+02
59	user93	{bcrypt}$2a$10$0sWgp6rBxhzRkKyCJK/IC.kYAYi/UhW2iS7dBjQRcAEImILPfaKQG	salt	USER	f	2023-10-23 10:14:44.841409+02
61		{bcrypt}$2a$10$ZQTCggn.3y/15rtsrTdvq.vSaywRiG7uCdvcVrBi7ecQU3TIh/i.G	salt	USER	f	2023-10-23 10:16:25.706808+02
62	user100	{bcrypt}$2a$10$GUKR.n9IeXyGhpVZZe49uOmzrjnjFVeeXj/..akEeKsE8wNXtlg1m	salt	USER	f	2023-10-23 11:38:25.102947+02
63	user101	{bcrypt}$2a$10$J7y7Ty2wp55nI4nIZk1m2eLf7lMpboxB4bXzkj.HEO0DC0wtB46wS	salt	USER	f	2023-10-23 11:40:35.006836+02
64	user102	{bcrypt}$2a$10$EEcktjS9vq/Rxu5nHawOqOIN7C93SNtkL8qU9mY9sgu/pR8V/neWe	salt	USER	f	2023-10-23 11:58:08.342347+02
65	user105	{bcrypt}$2a$10$mfZQqV66cB5RhKoIhlhLeOlSZrv63jyXWE.CU4WUSXhuaNO2Djuj2	salt	USER	f	2023-10-23 15:42:21.059236+02
66	user106	{bcrypt}$2a$10$ZXS6ERRqJIETmnEEs6bxlucTVL7MY8Q4Z1yH4TGzAvRm3w1ETq8.i	salt	USER	f	2023-10-23 15:44:08.879365+02
67	user107	{bcrypt}$2a$10$AO1KRpf65SfQ5xFm1qrW7uEgXf73E.3WyRzFG6GTwbBuLRKPzGlMy	salt	USER	f	2023-10-23 16:33:40.407215+02
68	user110	{bcrypt}$2a$10$PLhZkraKLZek3Cgznk.MC.bzHRnUVlCb/NQR9Hb.NhIQrjMVwGwje	salt	USER	f	2023-10-23 16:51:09.473411+02
\.


--
-- Data for Name: hidden_message; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hidden_message (id, message_uuid, created_at, hidden_by, reason) FROM stdin;
\.


--
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.message (uuid, author_id, thread_id, created_at, parent_uuid, body, image, is_active) FROM stdin;
1b5b5994-3064-419d-905e-c69befcea82c	1	2	2023-10-09 16:32:49.973306+02	\N	room2_thread2	\N	t
2339a441-1d48-45c1-85e8-d5f20431f956	1	1	2023-10-09 16:45:56.40403+02	\N	new	\\x	t
3339a441-1d48-45c1-85e8-d5f20431f956	1	1	2023-10-09 16:51:02.277872+02	2339a441-1d48-45c1-85e8-d5f20431f956	new	\\x	t
\.


--
-- Data for Name: reaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reaction (id, author_id, message_uuid, created_at, type) FROM stdin;
\.


--
-- Data for Name: reported_message; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reported_message (id, message_uuid, flagged_by, created_at, reason) FROM stdin;
\.


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.room (id, uuid, title, author_id, created_at, is_public, is_active, exited_at) FROM stdin;
1	32522a47-b5f3-4745-827f-6e6e1e5e754f	room1	1	2023-10-09 16:32:49.973306+02	t	t	\N
2	3b152638-2e44-4fe4-8aef-4d553cbd85c1	room2	1	2023-10-09 16:32:49.973306+02	t	t	\N
3	84553887-4b19-4e0c-b516-2cbb62da69dd	private room	12	2023-10-19 12:20:53.301363+02	f	t	\N
4	cdc27df4-ed32-4d03-8073-6e81868d8e89	subscribed room	12	2023-10-19 12:20:53.301363+02	f	t	\N
5	b6461fd4-ac5f-409c-ba31-bb80f8885cc4	owned room	40	2023-10-19 12:20:53.301363+02	f	t	\N
\.


--
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription (id, author_id, room_id, created_at, is_active, exited_at, privilege) FROM stdin;
5	40	4	2023-10-19 12:29:23.443457+02	t	\N	USER
\.


--
-- Data for Name: thread; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.thread (id, uuid, title, author_id, room_id, created_at, is_active, exited_at) FROM stdin;
1	0ac43728-5b00-4ec2-9399-47e4f40e0c54	thread1	1	1	2023-10-09 16:32:49.973306+02	t	\N
2	d3e41853-71dc-4699-ba59-38b722a578a6	thread2	1	2	2023-10-09 16:32:49.973306+02	t	\N
\.


--
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.author_id_seq', 68, true);


--
-- Name: hidden_message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hidden_message_id_seq', 1, false);


--
-- Name: reaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reaction_id_seq', 1, false);


--
-- Name: reported_message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reported_message_id_seq', 1, false);


--
-- Name: room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.room_id_seq', 5, true);


--
-- Name: subscription_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_id_seq', 5, true);


--
-- Name: thread_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.thread_id_seq', 2, true);


--
-- Name: author author_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_name_key UNIQUE (name);


--
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- Name: hidden_message hidden_message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hidden_message
    ADD CONSTRAINT hidden_message_pkey PRIMARY KEY (id);


--
-- Name: message message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (uuid);


--
-- Name: reaction reaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reaction
    ADD CONSTRAINT reaction_pkey PRIMARY KEY (id);


--
-- Name: reported_message reported_message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reported_message
    ADD CONSTRAINT reported_message_pkey PRIMARY KEY (id);


--
-- Name: room room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- Name: room room_uuid_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_uuid_key UNIQUE (uuid);


--
-- Name: subscription subscription_author_id_room_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_author_id_room_id_key UNIQUE (author_id, room_id);


--
-- Name: subscription subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);


--
-- Name: thread thread_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.thread
    ADD CONSTRAINT thread_pkey PRIMARY KEY (id);


--
-- Name: thread thread_uuid_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.thread
    ADD CONSTRAINT thread_uuid_key UNIQUE (uuid);


--
-- Name: hidden_message hidden_message_hidden_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hidden_message
    ADD CONSTRAINT hidden_message_hidden_by_fkey FOREIGN KEY (hidden_by) REFERENCES public.author(id);


--
-- Name: hidden_message hidden_message_message_uuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hidden_message
    ADD CONSTRAINT hidden_message_message_uuid_fkey FOREIGN KEY (message_uuid) REFERENCES public.message(uuid);


--
-- Name: message message_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(id);


--
-- Name: message message_parent_uuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_parent_uuid_fkey FOREIGN KEY (parent_uuid) REFERENCES public.message(uuid);


--
-- Name: message message_thread_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_thread_id_fkey FOREIGN KEY (thread_id) REFERENCES public.thread(id);


--
-- Name: reaction reaction_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reaction
    ADD CONSTRAINT reaction_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(id);


--
-- Name: reaction reaction_message_uuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reaction
    ADD CONSTRAINT reaction_message_uuid_fkey FOREIGN KEY (message_uuid) REFERENCES public.message(uuid);


--
-- Name: reported_message reported_message_flagged_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reported_message
    ADD CONSTRAINT reported_message_flagged_by_fkey FOREIGN KEY (flagged_by) REFERENCES public.author(id);


--
-- Name: reported_message reported_message_message_uuid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reported_message
    ADD CONSTRAINT reported_message_message_uuid_fkey FOREIGN KEY (message_uuid) REFERENCES public.message(uuid);


--
-- Name: room room_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(id);


--
-- Name: subscription subscription_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(id);


--
-- Name: subscription subscription_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: thread thread_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.thread
    ADD CONSTRAINT thread_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.author(id);


--
-- Name: thread thread_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.thread
    ADD CONSTRAINT thread_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- PostgreSQL database dump complete
--

