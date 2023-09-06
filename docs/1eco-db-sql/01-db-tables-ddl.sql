create or replace table `andrew`.file
(
    file_id            bigint auto_increment
        primary key,
    parent_id          bigint       null,
    mime_type          varchar(255) null comment '미디어 타입',
    size               bigint       null comment '파일크기',
    original_name      varchar(255) null comment '원본 파일명',
    s3_name            varchar(255) null comment '저장 파일 경로',
    save_name          varchar(255) null comment '저장 파일명',
    created_at         datetime(6)  null,
    updated_at         datetime(6)  null,
    sequence           int          null,
    is_deleted         tinyint      null,
    constraint file_file_file_id_fk
        foreign key (parent_id) references `1eco`.file (file_id)
)
    comment '파일';

create or replace table `andrew`.language
(
    lan_id        bigint auto_increment
        primary key,
    language_code varchar(100) not null comment '고유 코드',
    language_name varchar(100) null comment '언어명',
    constraint language_pk
        unique (language_code)
)
    comment '언어선택';

create or replace table `andrew`.location_country
          (
              code_id       bigint auto_increment comment '국가 번호'
              primary key,
              lan_id        bigint       null,
              code          varchar(255) null,
              country       varchar(255) null comment '국가명',
              continent     varchar(255) null comment '대륙명',
              location_code varchar(255) null,
              time_zone     varchar(255) null,
              is_service    tinyint(1)   null comment '서비스 지역 여부(서비스 중인지 아닌지)',
              constraint location_country_language_lan_id_fk
              foreign key (lan_id) references `1eco`.language (lan_id)
              )
              comment '국가';

create or replace table `andrew`.role
(
    role_id bigint auto_increment
        primary key,
    name    varchar(255) not null comment '권한명 (일반유저, 비즈니스, 탈퇴, 정지 등)'
)
    comment '유저 권한';

create or replace table `andrew`.admin_management
(
    admin_id   bigint auto_increment
        primary key,
    id         varchar(255) null comment '로그인 ID',
    role_id    bigint       null,
    name       varchar(255) null comment '관리자 이름',
    password   varchar(255) null comment '비밀번호',
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    is_deleted tinyint(1)   null,
    constraint admin_management_role_role_id_fk
        foreign key (role_id) references `1eco`.role (role_id)
);

create or replace table `andrew`.admin_faq_notice
(
    fn_id      bigint auto_increment
        primary key,
    admin_id   bigint       null comment '관리자 FK',
    code_id    bigint       null comment '국가코드 FK',
    file_id    bigint       null,
    board_type varchar(255) null comment '게시판 종류 (공지사항, FAQ 분류)',
    title      varchar(255) null comment '제목',
    body       longtext     null comment '내용',
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    is_deleted tinyint(1)   null,
    constraint admin_faq_notice_admin_management_admin_id_fk
        foreign key (admin_id) references `1eco`.admin_management (admin_id),
    constraint admin_faq_notice_country_code_code_id_fk
        foreign key (code_id) references `1eco`.location_country (code_id),
    constraint admin_faq_notice_file_file_id_fk
        foreign key (file_id) references `1eco`.file (file_id)
)
    comment 'FAQ 및 공지사항 테이블';

create or replace fulltext index admin_faq_notice_title_index
    on `1eco`.admin_faq_notice (title);

create or replace table `andrew`.admin_marketing_notification
(
    mn_id             bigint auto_increment
        primary key,
    admin_id          bigint       not null comment '관리자 FK',
    code_id           bigint       null comment '국가코드 FK',
    send_date         datetime(6)  null comment '전송일자',
    send_status       varchar(255) not null comment '전송 상태',
    send_message_type varchar(255) not null comment '메세지 타입 (sns, email)',
    send_reservation  varchar(255) not null comment '메세지 예약',
    target_role       varchar(255) not null comment '메세지 전송 대상 ( 일반유저, 비즈니스 유저 등)',
    title             varchar(255) not null comment '제목',
    body              longtext     not null comment '내용',
    constraint admin_marketing_notification_admin_management_admin_id_fk
        foreign key (admin_id) references `1eco`.admin_management (admin_id),
    constraint admin_marketing_notification_country_code_code_id_fk
        foreign key (code_id) references `1eco`.location_country (code_id)
)
    comment '마케팅 알림 관리';

create or replace fulltext index admin_marketing_notification_title_index
    on `1eco`.admin_marketing_notification (title);

create or replace table `andrew`.admin_term_conditions
(
    term_id      bigint auto_increment
        primary key,
    lan_id       bigint       null comment '언어 FK',
    admin_id     bigint       null comment '관리자 FK',
    terms_title  varchar(255) not null comment '약관명',
    terms_body   longtext     not null comment '약관 내용',
    is_required  tinyint      not null comment '필수 여부(true 필수, false 선택)',
    is_marketing tinyint(1)   null comment '마케팅 수신동의 여부',
    created_at   datetime(6)  null,
    updated_at   datetime(6)  null,
    is_deleted   tinyint(1)   null,
    constraint admin_term_conditions_admin_management_admin_id_fk
        foreign key (admin_id) references `1eco`.admin_management (admin_id),
    constraint admin_term_conditions_language_lan_id_fk
        foreign key (lan_id) references `1eco`.language (lan_id)
)
    comment '관리자 이용약관';

create or replace fulltext index admin_term_conditions_terms_title_index
    on `1eco`.admin_term_conditions (terms_title);

create or replace table `andrew`.shedlock
(
    name       varchar(64)  not null
        primary key,
    lock_until timestamp(3) null,
    locked_at  timestamp(3) null,
    locked_by  varchar(255) null
);

create or replace table `andrew`.user_info
(
    user_id      bigint auto_increment
        primary key,
    lan_id       bigint       not null comment '언어 설정',
    file_id      bigint       null comment '프로필 사진',
    email        varchar(255) null comment '이메일 주소 (유니크)',
    password     varchar(255) null comment '비밀번호',
    nickname     varchar(255) null comment '닉네임',
    phone_number varchar(255) null comment '핸드폰 번호',
    join_type    varchar(100) not null comment '최초 회원가입 타입',
    fcm_token    varchar(255) null comment '푸쉬알림 토큰',
    created_at   datetime(6)  null,
    updated_at   datetime(6)  null,
    is_deleted   tinyint(1)   null,
    constraint user_info_pk
        unique (phone_number, is_deleted),
    constraint user_info_pk2
        unique (email, is_deleted),
    constraint user_info_country_code_code_id_fk
        foreign key (code_id) references `1eco`.location_country (code_id),
    constraint user_info_file_file_id_fk
        foreign key (file_id) references `1eco`.file (file_id),
    constraint user_info_language_lan_id_fk
        foreign key (lan_id) references `1eco`.language (lan_id)
)
    comment '유저 정보';

create or replace table `andrew`.admin_blame
(
    blame_id    bigint auto_increment
        primary key,
    user_id     bigint       null comment '신고한 유저 ID',
    code_id     bigint       null comment '국가코드 FK',
    target_id   bigint       null comment '신고당한 대상 ID',
    target_type varchar(255) null comment '신고 종류 ( 장소, 게시글, 유저 등)',
    blame_type  varchar(255) null comment '신고 종류(카테고리)',
    blame_body  varchar(255) null comment '신고 내역(blame_type = ETC)',
    status      varchar(255) null comment '처리상태',
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,
    is_deleted  tinyint(1)   null,
    constraint admin_blame_country_code_code_id_fk
        foreign key (code_id) references `1eco`.location_country (code_id),
    constraint admin_blame_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment '게시글 신고 관리';

create or replace table `andrew`.notification
(
    not_id         bigint auto_increment
        primary key,
    target_user_id bigint                       null comment '알림 받는 유자',
    type           varchar(255)                 null comment '알림 종류 (활동, NFT, 공지)',
    message_body   varchar(255)                 not null comment '알림 내용',
    message_title  varchar(255)                 not null comment '알림 제목',
    data           longtext collate utf8mb4_bin null comment '전달 데이터'
        check (json_valid(`data`)),
    is_check       tinyint(1)                   null comment '읽음 여부',
    created_at     datetime(6)                  null,
    updated_at     datetime(6)                  null,
    constraint notification_user_info_user_id_fk
        foreign key (target_user_id) references `1eco`.user_info (user_id)
)
    comment '알림';

create or replace table `andrew`.notification_set
(
    sn_id        bigint auto_increment
        primary key,
    user_id      bigint  null,
    is_push      tinyint null comment '푸쉬알림 여부',
    is_marketing tinyint null comment '마케팅 알림 여부',
    constraint notification_set_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment '알림 셋팅';

create or replace table `andrew`.referral
(
    ref_id  bigint auto_increment
        primary key,
    user_id bigint       null,
    code    varchar(255) null comment '추천코드',
    constraint referral_pk
        unique (code),
    constraint referral_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment '추천인 ';

create or replace table `andrew`.role_user
(
    rc_id      bigint auto_increment
        primary key,
    role_id    bigint      not null,
    user_id    bigint      not null,
    created_at datetime(6) null,
    updated_at datetime(6) null,
    constraint role_user_pk
        unique (role_id, user_id),
    constraint role_user_role_role_id_fk
        foreign key (role_id) references `1eco`.role (role_id),
    constraint role_user_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment '각 유저별 권한 여부';

create or replace table `andrew`.sns_info
(
    sns_id     bigint auto_increment
        primary key,
    user_id    bigint       null,
    sns_email  varchar(255) not null comment 'sns 이메일',
    provider   varchar(255) not null comment 'sns 종류',
    access_id  varchar(255) not null comment 'accessToken에서 추출한 고유 ID',
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    constraint sns_info_pk
        unique (access_id, provider),
    constraint sns_info_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment 'sns 정보';

create or replace table `andrew`.tokens
(
    jwt_id      bigint auto_increment
        primary key,
    user_id     bigint     null,
    jwt_token   text       null comment '토큰의 값',
    jwt_expired tinyint(1) not null,
    constraint tokens_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment 'JWT 토큰 관리';

create or replace table `andrew`.user_check
(
    ck_id       bigint auto_increment
        primary key,
    user_id     bigint      not null,
    retry_count int         null,
    created_at  datetime(6) null,
    updated_at  datetime(6) null,
    constraint user_check_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment '유저 비밀번호 오류 횟수 체크';

create or replace index user_info_lan_id_index
    on `1eco`.user_info (lan_id);

create or replace table `andrew`.user_term
(
    ut_id      bigint auto_increment
        primary key,
    terms_code bigint      null comment '약관 명',
    user_id    bigint      null,
    is_agree   tinyint     not null comment '동의 여부',
    agree_date datetime(6) not null comment '동의 일자',
    constraint user_term_term_conditions_terms_code_fk
        foreign key (terms_code) references `1eco`.admin_term_conditions (term_id),
    constraint user_term_user_info_user_id_fk
        foreign key (user_id) references `1eco`.user_info (user_id)
)
    comment '이용 약관 동의 여부';

