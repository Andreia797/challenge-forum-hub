CREATE TABLE topics(
    id bigint AUTO_INCREMENT NOT NULL,
    title varchar(200) NOT NULL,
    message varchar(800) NOT NULL,
    created_at DATE NOT NULL,
    status tinyint NOT NULL,
    author varchar(100) NOT NULL,
    course_name varchar(100) NOT NULL,
    primary key(id)
);