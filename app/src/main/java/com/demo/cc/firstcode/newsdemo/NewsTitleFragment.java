package com.demo.cc.firstcode.newsdemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.cc.appclick.R;
import com.demo.cc.model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenXingLing on 2017/1/9.
 */

public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;//可以找到news_content_layout时，为双页模式
        } else {
            isTwoPane = false;//找不到news_content_layout时，为单页模式
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if (isTwoPane) {
            //如果是双页模式，刷新NewsContentFragment中的内容
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
                    .findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(), news.getContent());
        } else {
            //如果是单页模式，直接启动NewsContentActivity
            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        News news = new News();
        news.setTitle("端午妞妞11111111111端午妞妞1111111111111111");
        news.setContent("妞妞很怂，任何狗狗冲它吼叫，它基本从不还嘴。平时看到凶点的、脾气暴躁的汪，" +
                "我俩老远就开始绕路了，所以我们天天出门遛弯的过程，几乎就是一部受欺负史" +
                "。也许你会觉得，这样的妞妞，见了自己喜欢的汪，应该也是自卑、怯弱的。" +
                "但是，错！！！妞妞碰到它的朋友，瞬间就兴奋起来了，欢快的像个兔子，" +
                "它在几段关系里甚至还处于主导地位，喜欢站人家屁股上，喜欢蹭着人家耳朵。" +
                "有时我俩刚被厉害的汪凶完，我都还没缓过来，它转头看到心仪的狗，情绪丝毫不受影响，" +
                "开心的绕着人家打滚去了。用句鸡汤概括就是它总能大胆的去爱，像从未被伤害过。" +
                "也许正因它不懂，所以从不怕被瞧不起，只为自己喜欢和喜欢自己的一切活着。" +
                "不知蠢狗是怎样做到的，但真心的，愿你也能。爸好友的孩子结婚，相隔千里，他来让我帮他把礼金汇了过去，" +
                "现场发来了不少喜庆的视频，人声鼎沸，接踵摩肩，我们又远程见证了一场热闹的婚礼。" +
                "晚上跟爸妈坐着安安静静的吃了顿饭，关于感情，他俩已经不怎么催我了，" +
                "无数次的争执后终于换来了眼前的顺其自然。如今的状态是很少与人接触，" +
                "好友要么在天边，在眼前的也各自忙着彼此的事，少有会面。" +
                "除工作，几乎没参加过任何应酬，闲了就独自看看电影、跑跑步、吃吃饭。" +
                "如果以后我也会结婚的话，能告诉的人应该掰着手指都数得过来，" +
                "未必能坐满一桌，想想还挺悲凉的，但也很开心。没谁会关心我过的好不好了，有点孤独。" +
                "但也不用再幸福给任何人看了，真好，晚安");
        newsList.add(news);
        News news1 = new News();
        news1.setTitle("端午妞妞2222222222222端午妞妞222222222222222222");
        news1.setContent("爸好友的孩子结婚，相隔千里，他来让我帮他把礼金汇了过去，" +
                "现场发来了不少喜庆的视频，人声鼎沸，接踵摩肩，我们又远程见证了一场热闹的婚礼。" +
                "晚上跟爸妈坐着安安静静的吃了顿饭，关于感情，他俩已经不怎么催我了，" +
                "无数次的争执后终于换来了眼前的顺其自然。如今的状态是很少与人接触，" +
                "好友要么在天边，在眼前的也各自忙着彼此的事，少有会面。" +
                "除工作，几乎没参加过任何应酬，闲了就独自看看电影、跑跑步、吃吃饭。" +
                "如果以后我也会结婚的话，能告诉的人应该掰着手指都数得过来，" +
                "未必能坐满一桌，想想还挺悲凉的，但也很开心。没谁会关心我过的好不好了，有点孤独。" +
                "但也不用再幸福给任何人看了，真好，晚安");
        newsList.add(news1);

        return newsList;
    }
}
