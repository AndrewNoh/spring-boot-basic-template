-- language
INSERT INTO `andrew`.language (lan_id, language_code, language_name) VALUES (1, 'ko', '한국어');
INSERT INTO `andrew`.language (lan_id, language_code, language_name) VALUES (2, 'en', 'English');
INSERT INTO `andrew`.language (lan_id, language_code, language_name) VALUES (3, 'vi', 'tiếng Việt');

-- country_code
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (1, 1, '82', 'South Korea', 'Asia', 'KR', 'Asia/Seoul', 1);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (2, 2, '886', 'Taiwan', 'Asia', 'TW', 'Asia/Taipei', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (3, 2, '60', 'Malaysia', 'Asia', 'MY', 'Asia/Kuala_Lumpur', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (4, 3, '84', 'Vietnam', 'Asia', 'VN', 'Asia/Ho_Chi_Minh', 1);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (5, 2, '62', 'Indonesia', 'Asia', 'ID', 'Asia/Jakarta', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (6, 2, '81', 'Japan', 'Asia', 'JP', 'Asia/Tokyo', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (7, 2, '86', 'China', 'Asia', 'CN', 'Asia/Shanghai', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (8, 2, '66', 'Thailand', 'Asia', 'TH', 'Asia/Bangkok', 1);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (9, 2, '63', 'Philippines', 'Asia', 'PH', 'Asia/Manila', 1);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (10, 2, '852', 'Hong Kong', 'Asia', 'HK', 'Asia/Hong_Kong', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (11, 2, '91', 'India', 'Asia', 'IN', 'Asia/Kolkata', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (12, 2, '65', 'Singapore', 'Asia', 'SG', 'Asia/Singapore', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (13, 2, '31', 'Netherlands', 'Europe', 'NL', 'Europe/Amsterdam', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (14, 2, '49', 'Germany', 'Europe', 'DE', 'Europe/Berlin', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (15, 2, '7', 'Russia', 'Europe', 'RU', 'Europe/Moscow', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (16, 2, '41', 'Switzerland', 'Europe', 'CH', 'Europe/Zurich', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (17, 2, '34', 'Spain', 'Europe', 'ES', 'Europe/Madrid', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (18, 2, '44', 'United Kingdom', 'Europe', 'GB', 'Europe/London', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (19, 2, '43', 'Austria', 'Europe', 'AT', 'Europe/Vienna', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (20, 2, '420', 'Czech Republic', 'Europe', 'CZ', 'Europe/Prague', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (21, 2, '48', 'Poland', 'Europe', 'PL', 'Europe/Warsaw', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (22, 2, '33', 'France', 'Europe', 'FR', 'Europe/Paris', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (23, 2, '30', 'Greece', 'Europe', 'GR', 'Europe/Athens', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (24, 2, '36', 'Hungary', 'Europe', 'HU', 'Europe/Budapest', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (25, 2, '32', 'Belgium', 'Europe', 'BE', 'Europe/Brussels', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (26, 2, '351', 'Portugal', 'Europe', 'PT', 'Europe/Lisbon', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (27, 2, '353', 'Ireland', 'Europe', 'IE', 'Europe/Dublin', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (28, 2, '354', 'Iceland', 'Europe', 'IS', 'Atlantic/Reykjavik', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (29, 2, '356', 'Malta', 'Europe', 'MT', 'Europe/Malta', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (30, 2, '358', 'Finland', 'Europe', 'FI', 'Europe/Helsinki', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (31, 2, '377', 'Monaco', 'Europe', 'MC', 'Europe/Monaco', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (32, 2, '386', 'Slovenia', 'Europe', 'SI', 'Europe/Ljubljana', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (33, 2, '39', 'Italy', 'Europe', 'IT', 'Europe/Rome', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (34, 2, '45', 'Denmark', 'Europe', 'DK', 'Europe/Copenhagen', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (35, 2, '966', 'Saudi Arabia', 'Middle East', 'SA', 'Asia/Riyadh', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (36, 2, '963', 'Syria', 'Middle East', 'SY', 'Asia/Damascus', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (37, 2, '968', 'Oman', 'Middle East', 'OM', 'Asia/Muscat', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (38, 2, '962', 'Jordan', 'Middle East', 'JO', 'Asia/Amman', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (39, 2, '964', 'Iraq', 'Middle East', 'IQ', 'Asia/Baghdad', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (40, 2, '98', 'Iran', 'Middle East', 'IR', 'Asia/Tehran', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (41, 2, '972', 'Israel', 'Middle East', 'IL', 'Asia/Jerusalem', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (42, 2, '974', 'Qatar', 'Middle East', 'QA', 'Asia/Qatar', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (43, 2, '965', 'Kuwait', 'Middle East', 'KW', 'Asia/Kuwait', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (44, 2, '90', 'Turkey', 'Middle East', 'TR', 'Europe/Istanbul', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (45, 2, '1', 'United States', 'North America', 'US', 'America/New_York', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (46, 2, '1', 'Canada', 'North America', 'CA', 'America/Toronto (for Eastern Time)', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (47, 2, '502', 'Guatemala', 'South America', 'GT', 'America/Guatemala', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (48, 2, '52', 'Mexico', 'South America', 'MX', 'America/Mexico_City', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (49, 2, '55', 'Brazil', 'South America', 'BR', 'America/Sao_Paulo', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (50, 2, '54', 'Argentina', 'South America', 'AR', 'America/Buenos_Aires', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (51, 2, '593', 'Ecuador', 'South America', 'EC', 'America/Guayaquil', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (52, 2, '56', 'Chile', 'South America', 'CL', 'America/Santiago', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (53, 2, '57', 'Colombia', 'South America', 'CO', 'America/Bogota', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (54, 2, '507', 'Panama', 'South America', 'PA', 'America/Panama', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (55, 2, '595', 'Paraguay', 'South America', 'PY', 'America/Asuncion', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (56, 2, '51', 'Peru', 'South America', 'PE', 'America/Lima', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (57, 2, '1-671', 'Guam', 'Oceania', 'GU', 'Pacific/Guam', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (58, 2, '64', 'New Zealand', 'Oceania', 'NZ', 'Pacific/Auckland', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (59, 2, '678', 'Vanuatu', 'Oceania', 'VU', 'Pacific/Efate', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (60, 2, '685', 'Samoa', 'Oceania', 'WS', 'Pacific/Apia', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (61, 2, '677', 'Solomon Islands', 'Oceania', 'SB', 'Pacific/Guadalcanal', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (62, 2, '61', 'Australia', 'Oceania', 'AU', 'Australia/Sydney', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (63, 2, '686', 'Kiribati', 'Oceania', 'KI', 'Pacific/Tarawa', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (64, 2, '675', 'Papua New Guinea', 'Oceania', 'PG', 'Pacific/Port_Moresby', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (65, 2, '680', 'Palau', 'Oceania', 'PW', 'Pacific/Palau', 0);
INSERT INTO `andrew`.location_country (code_id, lan_id, code, country, continent, location_code, time_zone, is_service) VALUES (66, 2, '679', 'Sebum', 'Oceania', 'FJ', 'UTC', 0);

-- role
INSERT INTO `andrew`.role (role_id, name) VALUES (1, 'NORMAL');
INSERT INTO `andrew`.role (role_id, name) VALUES (2, 'UNIQUE');
INSERT INTO `andrew`.role (role_id, name) VALUES (5, 'ROLE_SUSPENDED');
INSERT INTO `andrew`.role (role_id, name) VALUES (6, 'ROLE_WITHDRAWN');
INSERT INTO `andrew`.role (role_id, name) VALUES (7, 'ROLE_ROOT');
INSERT INTO `andrew`.role (role_id, name) VALUES (8, 'ROLE_ADMIN');
INSERT INTO `andrew`.role (role_id, name) VALUES (9, 'ROLE_VIEWER');

-- root admin
INSERT INTO `andrew`.admin_management (admin_id, id, role_id, name, password, created_at, updated_at, is_deleted) VALUES (1, 'admin', 7, 'andrew', '$2a$10$lo96pd/1e2sxkgobLMFpa.rX6iyMqUUWxbSmJcPwuo/jvoXRRGbKq', '2023-06-07 12:03:14.000000', '2023-06-07 12:03:16.000000', 0);

-- terms
INSERT INTO `andrew`.admin_term_conditions (term_id, lan_id, admin_id, terms_title, terms_body, is_required, is_marketing, created_at, updated_at, is_deleted) VALUES (1, 1, 1, '이용약관', '이용약관', 1, 0, '2023-06-30 13:44:48.000000', '2023-06-30 13:44:51.000000', 0);
INSERT INTO `andrew`.admin_term_conditions (term_id, lan_id, admin_id, terms_title, terms_body, is_required, is_marketing, created_at, updated_at, is_deleted) VALUES (2, 1, 1, '개인정보 수집 및 이용', '개인정보 수집 및 이용', 1, 0, '2023-06-30 13:44:49.000000', '2023-06-30 13:44:52.000000', 0);
INSERT INTO `andrew`.admin_term_conditions (term_id, lan_id, admin_id, terms_title, terms_body, is_required, is_marketing, created_at, updated_at, is_deleted) VALUES (3, 1, 1, '마케팅 정보 수신', '마케팅 정보 수신', 0, 1, '2023-06-30 13:44:50.000000', '2023-06-30 13:44:52.000000', 0);
INSERT INTO `andrew`.admin_term_conditions (term_id, lan_id, admin_id, terms_title, terms_body, is_required, is_marketing, created_at, updated_at, is_deleted) VALUES (22, 2, 1, 'Terms of Use', 'This refers to the legal agreement that outlines the conditions, rules, and guidelines that users must comply with when accessing or using a particular service, website, or application. It typically covers aspects such as user responsibilities, prohibited activities, intellectual property rights, liability disclaimers, and dispute resolution mechanisms.', 1, 0, '2023-06-30 04:58:05.181844', '2023-06-30 04:58:05.181844', 0);
INSERT INTO `andrew`.admin_term_conditions (term_id, lan_id, admin_id, terms_title, terms_body, is_required, is_marketing, created_at, updated_at, is_deleted) VALUES (23, 2, 1, 'Collection and Use of Personal Information', 'This term refers to the policy that outlines how an organization or service provider collects, stores, processes, and utilizes personal information of users or customers. It typically describes the types of data collected, the purposes for which the data is used, any third parties with whom the data may be shared, data retention periods, and measures taken to protect user privacy.', 1, 0, '2023-06-30 04:58:16.743319', '2023-06-30 04:58:16.743319', 0);
INSERT INTO `andrew`.admin_term_conditions (term_id, lan_id, admin_id, terms_title, terms_body, is_required, is_marketing, created_at, updated_at, is_deleted) VALUES (25, 1, 1, '테스트용', '생성되었다', 1, 0, '2023-07-26 02:27:35.465272', '2023-07-27 07:59:43.290080', 0);