package ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
