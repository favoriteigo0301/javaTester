package study.sample.controller;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import study.sample.config.SampleConfiguration;
import study.sample.dao.SampleDao;
import study.sample.entity.SampleMemoEntity;
import study.sample.repository.SampleMemoRepository;
import study.sample.service.SampleMemoService;
import study.sample.form.SampleMemoRequest;

import java.util.List;

/**
 *  SpringBootの動作を確認するためのサンプルクラス
 */
@Controller
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    SampleMemoRepository memoRepository;

    /**
     * SpringJdbcを動かすためのDAO(用途を理解するために記述）
     */
    @Autowired
    SampleDao memoDao;

    @Autowired
    SampleMemoService service;

    @Autowired
    SampleConfiguration sampleConfiguration;

    @GetMapping("/test")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        System.out.println("コンフィグ" + sampleConfiguration.getUserNameSize());
        return mav;
    }

    @GetMapping("/count")
    public ModelAndView count(ModelAndView mav){
        mav.setViewName("index");
        System.out.println("メモテーブル件数"+memoRepository.count());
        return mav;
    }

    @GetMapping("/jdbc/count")
    public ModelAndView jdbcCount(ModelAndView mav) {
        mav.setViewName("index");
        System.out.println("jdbcでのcount文"+ memoDao.count() );
        return mav;
    }

    @GetMapping("/memo")
    public String sampleMemo(@ModelAttribute("request") SampleMemoRequest request, ModelAndView mav) {
        mav.addObject("request", request);
        return "sample_memo";
    }

    @PostMapping("/memo/index")
    public ModelAndView memoIndex(@Validated @ModelAttribute("request") SampleMemoRequest request, BindingResult result, ModelAndView mav)  {
        mav.addObject("request",request);
        if (!result.hasErrors()) {
            service.regist(request);
        }
        mav.setViewName("sample_memo");
        return mav;
    }

    @GetMapping("/api/memo/{id}")
    public ModelAndView apiMemo(@ModelAttribute("request") SampleMemoRequest request, BindingResult result, ModelAndView mav, @PathVariable(name = "id") long id) {
        mav.setViewName("sample_memo");
        service.getSampleMemo();
        return mav;
    }

    @GetMapping("/memo/list")
    public ModelAndView showMemoList(ModelAndView mav) {
        SampleMemoEntity[] sampleMemos = service.getList();

        mav.setViewName("sample_memo_list");
        return mav;
    }
}
