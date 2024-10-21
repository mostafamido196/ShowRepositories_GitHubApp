//package com.example.odcgithubrepoapp.data.repository
//import Item
//import Owner
//import androidx.paging.PagingData
//import com.example.odcgithubrepoapp.data.data_sources.local.GithubLocalDataSource
//import com.example.odcgithubrepoapp.data.data_sources.remote.GithubRemoteDataSource
//import com.example.odcgithubrepoapp.data.data_sources.remote.retrofit.data_model.repo_list.GithubReposDataModel
//import com.example.odcgithubrepoapp.data.mapper.to_domain.toGithubReposDomainModel
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.mockk
//import junit.framework.TestCase.assertEquals
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Test
//
//class GithubReposRepositoryImplTest {
//
//    private val githubRemoteDataSource = mockk<GithubRemoteDataSource>()
//    private val githubLocalDataSource = mockk<GithubLocalDataSource>() // If needed
//    private lateinit var repository: GithubReposRepositoryImpl
//
//    @Before
//    fun setUp() {
//        repository = GithubReposRepositoryImpl(
//            githubRemoteDataSource = githubRemoteDataSource,
//            githubLocalDataSource = githubLocalDataSource
//        )
//    }
//
//    @Test
//    fun `test searchRepoList returns PagingData`() = runTest {
//        // Prepare a mock response similar to the JSON structure you provided
//        val searchKey = "php"
//        val mockResponse = GithubReposDataModel(
//            totalCount = 1152027,
//            incompleteResults = false,
//            items = listOf(
//                Item(
//                    id = 22183003,
//                    name = "php",
//                    fullName = "docker-library/php",
//                    owner = Owner(
//                        login = "docker-library",
//                        id = 7739233,
//                        avatarUrl = "https://avatars.githubusercontent.com/u/7739233?v=4",
//                        reposUrl = "",
//                        htmlUrl = "",
//                        url = "",
//                        type = "",
//                        nodeId = "",
//                        gistsUrl = "",
//                        receivedEventsUrl = "",
//                        eventsUrl = "",
//                        siteAdmin = false, // Changed to boolean
//                        followersUrl = "",
//                        subscriptionsUrl = "", // Changed to string
//                        followingUrl = "",
//                        gravatarId = "111",
//                        starredUrl = "",
//                        organizationsUrl = ""
//                    ),
//                    description = "Docker Official Image packaging for PHP",
//                    htmlUrl = "https://github.com/docker-library/php",
//                    stargazersCount = 3821,
//                    watchersCount = 3821,
//                    language = "Shell",
//                    forksCount = 1999,
//                    openIssuesCount = 34,
//                    allowForking = true,  // Missing fields added with sample values
//                    archiveUrl = "",
//                    archived = false,
//                    assigneesUrl = "",
//                    blobsUrl = "",
//                    branchesUrl = "",
//                    cloneUrl = "",
//                    collaboratorsUrl = "",
//                    commentsUrl = "",
//                    commitsUrl = "",
//                    compareUrl = "",
//                    contentsUrl = "",
//                    contributorsUrl = "",
//                    createdAt = "",
//                    defaultBranch = "",
//                    deploymentsUrl = "",
//                    disabled = false,
//                    downloadsUrl = "",
//                    fork = false,
//                    forks = 1999,
//                    forksUrl = "",
//                    gitCommitsUrl = "",
//                    gitRefsUrl = "",
//                    gitTagsUrl = "",
//                    gitUrl = "",
//                    hasDiscussions = false,
//                    hasDownloads = true,
//                    hasIssues = true,
//                    hasPages = false,
//                    hasProjects = false,
//                    hasWiki = true,
//                    homepage = null,
//                    hooksUrl = "",
//                    isTemplate = false,
//                    issueCommentUrl = "",
//                    issueEventsUrl = "",
//                    issuesUrl = "",
//                    keysUrl = "",
//                    labelsUrl = "",
//                    languagesUrl = "",
//                    license = null,
//                    mergesUrl = "",
//                    milestonesUrl = "",
//                    mirrorUrl = null,
//                    nodeId = "",
//                    notificationsUrl = "",
//                    openIssues = 34,
//                    privateRepo = false,
//                    pullsUrl = "",
//                    pushedAt = "",
//                    releasesUrl = "",
//                    score = 0.0,
//                    size = 0,
//                    sshUrl = "",
//                    stargazersUrl = "",
//                    statusesUrl = "",
//                    subscribersUrl = "",
//                    subscriptionUrl = "",
//                    svnUrl = "",
//                    tagsUrl = "",
//                    teamsUrl = "",
//                    topics = listOf(),
//                    treesUrl = "",
//                    updatedAt = "",
//                    url = "",
//                    visibility = "",
//                    watchers = 3821,
//                    webCommitSignoffRequired = false,
//                    eventsUrl = ""
//                )
//            )
//        )
//
//
//        // Mock the remote data source response
//        coEvery { githubRemoteDataSource.searchRepositories(searchKey = searchKey) } returns mockResponse
//
//        // Call the repository method
//        val result = repository.searchRepoList(searchKey).first()
//
//        // Prepare expected PagingData based on mock response
//        val expectedData = PagingData.from(mockResponse.items.map { it.toGithubReposDomainModel() })
//
//        // Verify the results match the expected PagingData
//        assertEquals(expectedData, result)
//
//        // Verify that the remote data source was called with the correct parameter
//        coVerify { githubRemoteDataSource.searchRepositories(searchKey = searchKey) }
//    }
//}
