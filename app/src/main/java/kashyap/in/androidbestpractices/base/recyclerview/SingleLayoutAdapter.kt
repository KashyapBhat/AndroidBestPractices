package kashyap.`in`.androidbestpractices.base.recyclerview

open class SingleLayoutAdapter(
    private val layoutId: Int,
    clickListener: RVItemClickListener,
    dataSetChanged: DataSetChanged?
) : MyBaseAdapter(clickListener, dataSetChanged) {

    override fun getLayoutIdForPosition(position: Int, dataAtThatPostion: Any): Int {
        return layoutId
    }
}