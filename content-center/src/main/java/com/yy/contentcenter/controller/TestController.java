package com.yy.contentcenter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    @LoadBalanced
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    Random random;

    @GetMapping(value = "/test")
    public List<ServiceInstance> getDiscoveryClient() {
        return discoveryClient.getInstances("user-center");
    }

    @GetMapping(value = "/hello/{string}")
    public String helloFromUser(@PathVariable String string) {
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        String s;
        random = new Random();
//        int i = random.nextInt(instances.size());
//        s = instances.get(i).getUri().toString() + "/echo/" + string;
//        try {
//            s = instances.stream().map(instance -> instance.getUri().toString() + "/echo/" + string)
//                    .findFirst()
//                    .orElseThrow(() -> new IllegalArgumentException("当前没有实例"));
        s = instances.stream().map(instance -> instance.getUri().toString() + "/echo/" + string)
                .collect(Collectors.toList()).get(ThreadLocalRandom.current().nextInt(instances.size()));
        log.info("目标地址:{}", s);
//        } catch (Exception ignored) {
//            return "没有user实例";
//        }
        return restTemplate.getForObject(s, String.class);
    }

    @GetMapping(value = "/ribbon/{string}")
    public String callByRibbon(@PathVariable String string) {
        return restTemplate.getForObject("http://user-center/echo/" + string, String.class);
    }


}
