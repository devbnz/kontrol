// Generated code from Butter Knife. Do not modify!
package com.pegelstaende;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MarketDetailsActivity$$ViewInjector {
  public static void inject(Finder finder, final com.pegelstaende.MarketDetailsActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296344, "field 'name'");
    target.name = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296343, "field 'fetchid'");
    target.fetchid = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296346, "field 'subscriber'");
    target.subscriber = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296348, "field 'videos'");
    target.videos = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296347, "field 'comments'");
    target.comments = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296345, "field 'views'");
    target.views = (android.widget.TextView) view;
  }

  public static void reset(com.pegelstaende.MarketDetailsActivity target) {
    target.name = null;
    target.fetchid = null;
    target.subscriber = null;
    target.videos = null;
    target.comments = null;
    target.views = null;
  }
}
