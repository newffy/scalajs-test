package ru.simplesys
package smartclient
package visual
package grids

import macrojs._
import cmntypes._
import smartclient.visual.common.{DataBoundComponentProps, DataBoundComponent}
import smartclient.visual.layouts.{VLayoutProps, VLayout}


trait ListGrid extends VLayout with DataBoundComponent {

}

class ListGridProps extends VLayoutProps with DataBoundComponentProps

object ListGrid extends SCApply[ListGrid, ListGridProps]
