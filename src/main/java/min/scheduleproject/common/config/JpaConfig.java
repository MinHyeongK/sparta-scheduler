package min.scheduleproject.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // AuditorAware 설정이나 추가 JPA 관련 설정 필요 시 작성
}